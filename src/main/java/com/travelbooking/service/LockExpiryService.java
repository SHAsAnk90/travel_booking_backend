package com.travelbooking.service;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.travelbooking.entity.*;
import com.travelbooking.repository.BookingRepository;
import com.travelbooking.repository.ResourceLockRepository;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.transaction.Transactional;


@Service
public class LockExpiryService {

    private final ResourceLockRepository resourceLockRepository;
    private final BookingRepository bookingRepository;

    public LockExpiryService(ResourceLockRepository resourceLockRepository, BookingRepository bookingRepository) {
        this.resourceLockRepository = resourceLockRepository;
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    @Transactional
    public void expireLocks()
    {
        List<ResourceLock> expiredLocks = resourceLockRepository.findByLockExpiryTimeBeforeAndStatus(LocalDateTime.now(), LockStatus.LOCKED);
        for(ResourceLock lock : expiredLocks)
        {
            lock.setStatus(LockStatus.RELEASED);
            Booking booking = bookingRepository.findById(lock.getBookingId()).orElse(null);
            if(booking!=null&&booking.getStatus()==BookingStatus.INITIATED)
            {
                booking.setStatus(BookingStatus.CANCELLED);
                bookingRepository.save(booking);
            }
        }
    }

}
