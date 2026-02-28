package com.travelbooking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelbooking.entity.*;
import java.util.List;
import java.util.Optional;
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long>{
    

    Optional<Resource> findByTransportAndSectionAndResourceNumber(Transport transport,
                                           String section, String resourceNumber);

     
    List<Resource> findByTransport(Transport transport);



}
