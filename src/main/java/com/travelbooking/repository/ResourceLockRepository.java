package com.travelbooking.repository;

import com.travelbooking.entity.ResourceLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface ResourceLockRepository extends JpaRepository<ResourceLock, Long> {

    Optional<ResourceLock> findByResource_ResourceIdAndJourneyDate(Long resourceId, LocalDateTime journeyDate);
    void deleteByLockExpiryTimeBefore(LocalDateTime time);

}
