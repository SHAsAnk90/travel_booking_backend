package com.travelbooking.service;
import com.travelbooking.dto.response.TransportResponseDTO;
import com.travelbooking.dto.response.TransportResourceResponseDTO;
import com.travelbooking.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchService {
    List<TransportResponseDTO> searchTransport(String source, String destination);

    public TransportResourceResponseDTO getTransportResourcesWithAvailablity(String transportCode, LocalDateTime journeyDate);
}

