package com.travelbooking.service;
import com.travelbooking.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityService {


    List<Resource> getAvailableResources(Long transportId, LocalDateTime journeyDate);
    public boolean isAvailable(Resource resource, LocalDateTime date) ;
}
