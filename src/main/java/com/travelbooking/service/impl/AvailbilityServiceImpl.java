package com.travelbooking.service.impl;

import com.travelbooking.entity.*;
import com.travelbooking.exception.ResourceNotFoundException;
import com.travelbooking.repository.*;
import com.travelbooking.service.AvailabilityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailbilityServiceImpl implements AvailabilityService {

    private final TransportRepository transportRepository;
    private final ResourceRepository resourceRepository;
    private final BookingRepository bookingRepository;
    private final ResourceLockRepository lockRepository;

    public AvailbilityServiceImpl(TransportRepository transportRepository,
                                   ResourceRepository resourceRepository,
                                   BookingRepository bookingRepository,
                                   ResourceLockRepository lockRepository) {
        this.transportRepository = transportRepository;
        this.resourceRepository = resourceRepository;
        this.bookingRepository = bookingRepository;
        this.lockRepository = lockRepository;
    }

    @Override
    public List<Resource> getAvailableResources(Long transportId,
                                                LocalDateTime journeyDate) {

        Transport transport = transportRepository.findById(transportId)
                .orElseThrow(() -> new ResourceNotFoundException("Transport not found"));

        List<Resource> allResources = resourceRepository.findByTransport(transport);

        return allResources.stream()
                .filter(resource -> isAvailable(resource, journeyDate))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAvailable(Resource resource, LocalDateTime date) {

        // Check confirmed bookings
        List<Booking> bookings =
                bookingRepository.findByResourceAndJourneyDate(resource, date);

        boolean booked = bookings.stream()
                .anyMatch(b -> b.getStatus() == BookingStatus.CONFIRMED);

        if (booked) return false;

        // Check active lock
        return lockRepository
                .findByResource_ResourceIdAndJourneyDate(resource.getResourceId(), date)
                .map(lock -> lock.getStatus() != LockStatus.LOCKED)
                .orElse(true);
    }
}
