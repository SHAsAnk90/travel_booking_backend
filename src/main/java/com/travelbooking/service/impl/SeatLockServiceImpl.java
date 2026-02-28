package com.travelbooking.service.impl;
import com.travelbooking.entity.*;
import com.travelbooking.exception.BusinessException;
import com.travelbooking.exception.ResourceNotFoundException;
import com.travelbooking.repository.*;
import com.travelbooking.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SeatLockServiceImpl implements SeatLockService{

    private final ResourceRepository resourceRepository;
    private final ResourceLockRepository lockRepository;

    public SeatLockServiceImpl(ResourceRepository resourceRepository, ResourceLockRepository lockRepository)
    {
        this.lockRepository = lockRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    @Transactional
    public void lockSeats(Long resourceId, LocalDateTime journeyDate, Long bookingId)
    {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(()-> new ResourceNotFoundException("Resource not found"));
        ResourceLock lock = new ResourceLock();
        lock.setResource(resource);
        lock.setJourneyDate(journeyDate);
        lock.setBookingId(bookingId);
        lock.setLockExpiryTime(LocalDateTime.now().plusMinutes(5));
        lock.setStatus(LockStatus.LOCKED);
        try{
        lockRepository.save(lock);
        }catch(Exception e)
        {
            throw new BusinessException("Seat already locked by another user");
        }

    }
    @Override
    @Transactional
     public void releaseSeats(Long resourceId,
                            LocalDateTime journeyDate) {

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        lockRepository.findByResource_ResourceIdAndJourneyDate(resourceId, journeyDate)
                .ifPresent(lock -> {
                    lock.setStatus(LockStatus.RELEASED);
                    lockRepository.save(lock);
                });
    }
     


}
