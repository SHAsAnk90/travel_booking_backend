package com.travelbooking.repository;
import com.travelbooking.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user);
    List<Booking> findByResourceAndJourneyDate(Resource Resource, LocalDateTime journeyDate);
    @Query("""
            SELECT b.resource.resourceId FROM Booking b
            WHERE b.resource.transport = :transport
            AND b.journeyDate = :journeyDate
            AND b.status = :status
            """)
            List<Long> findBookedResourceIds(@Param("transport") Transport transport,@Param("journeyDate") LocalDateTime journeyDate,@Param("status") BookingStatus status);
}
