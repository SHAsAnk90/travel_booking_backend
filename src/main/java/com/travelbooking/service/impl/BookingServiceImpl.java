package com.travelbooking.service.impl;

import com.travelbooking.dto.request.BookingRequestDTO;
import com.travelbooking.dto.response.BookingResponseDTO;
import com.travelbooking.entity.*;
import com.travelbooking.exception.BusinessException;
import com.travelbooking.exception.ResourceNotFoundException;
import com.travelbooking.repository.*;
import com.travelbooking.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ResourceRepository resourceRepository;
    private final SeatLockService seatLockService;
    private final AvailabilityService availabilityService;
    private final UserRepository userRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              ResourceRepository resourceRepository,
                              AvailabilityService availabilityService,
                              SeatLockService seatLockService, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.resourceRepository = resourceRepository;
        this.availabilityService = availabilityService;
        this.seatLockService = seatLockService;
        this.userRepository = userRepository;

    }

    @Override
    @Transactional
    public BookingResponseDTO initiateBooking(BookingRequestDTO bookingRequest) {

        // 1️⃣ Check availability
        Resource resource = resourceRepository.findById(bookingRequest.getResourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));         

        boolean available = availabilityService
                .isAvailable(resource, bookingRequest.getJourneyDate());

        if (!available) {
            throw new BusinessException("No seats available");
        }

        // 2️⃣ Create booking (INITIATED)
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setResource(resource);
        booking.setJourneyDate(bookingRequest.getJourneyDate());
        booking.setAmount(bookingRequest.getAmount());
        booking.setStatus(BookingStatus.INITIATED);

        bookingRepository.save(booking);

        // 3️⃣ Lock resource
        seatLockService.lockSeats(
                resource.getResourceId(),
                bookingRequest.getJourneyDate(),
                booking.getBookingId()
        );

        return mapToResponseDTO(booking);
    }

    @Override
    @Transactional
    public BookingResponseDTO confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (booking.getStatus() != BookingStatus.INITIATED) {
            throw new BusinessException("Booking cannot be confirmed");
        }

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        seatLockService.releaseSeats(booking.getResource().getResourceId(), booking.getJourneyDate());

        return mapToResponseDTO(booking);
    }

    @Override
    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        seatLockService.releaseSeats(booking.getResource().getResourceId(), booking.getJourneyDate());
        return mapToResponseDTO(booking);
    }

    private BookingResponseDTO mapToResponseDTO(Booking booking) {
        return new BookingResponseDTO(
                booking.getBookingId(),
                booking.getUser().getUserId(),
                booking.getResource().getResourceId(),
                booking.getJourneyDate(),
                booking.getAmount(),
                booking.getStatus().name(),
                booking.getCreatedAt()
        );
    }
}
