package com.travelbooking.service;
import com.travelbooking.entity.*;
import com.travelbooking.dto.request.*;
import com.travelbooking.dto.response.*;
import com.travelbooking.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TransportService {
    
    
    private final TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }


    public TransportResponseDTO createTransport(TransportRequestDTO transportRequest) {
        if (transportRepository.findByTransportCode(transportRequest.getTransportCode()).isPresent()) {
            throw new IllegalArgumentException("Transport code already exists");
        }

        Transport transport = new Transport();
        transport.setTransportType(transportRequest.getTransportType());
        transport.setTransportCode(transportRequest.getTransportCode());
        transport.setSource(transportRequest.getSource());
        transport.setDestination(transportRequest.getDestination());
        transport.setDepartureTime(transportRequest.getDepartureTime());
        transport.setArrivalTime(transportRequest.getArrivalTime());

        Transport savedTransport = transportRepository.save(transport);
        return new TransportResponseDTO(savedTransport);
    }

    public List<TransportResponseDTO> getAllTransport()
    {
        List<Transport> transport = transportRepository.findAll();
        List<TransportResponseDTO> responseList = new ArrayList<>();
        for(Transport t : transport) {
            responseList.add(new TransportResponseDTO(t));
        }
        return responseList;
    }

}
