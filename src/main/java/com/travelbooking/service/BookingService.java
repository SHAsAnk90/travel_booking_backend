package com.travelbooking.service;
import com.travelbooking.dto.request.BookingRequestDTO;
import com.travelbooking.dto.response.BookingResponseDTO;


public interface BookingService {

    BookingResponseDTO initiateBooking(BookingRequestDTO bookingRequest);

    BookingResponseDTO confirmBooking(Long bookingId);

    BookingResponseDTO cancelBooking(Long bookingId);
}
