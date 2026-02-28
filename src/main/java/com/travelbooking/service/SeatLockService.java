package com.travelbooking.service;

import java.time.LocalDateTime;


public interface SeatLockService {

    void lockSeats(Long resourceId, LocalDateTime journeyDate, Long bookingId);
    void releaseSeats(Long bookingId, LocalDateTime journeyDate);

}
