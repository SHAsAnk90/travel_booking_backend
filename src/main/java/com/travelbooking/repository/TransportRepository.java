package com.travelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.travelbooking.entity.*;
import java.util.List;
import java.util.Optional;


@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    Optional<Transport> findByTransportCode(String transportCode);
    List<Transport> findBySourceAndDestinationAndTransportType(String source, String destination, TransportType transportType);
    List<Transport> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);

}
