package com.travelbooking.service.impl;

import com.travelbooking.dto.response.ResourceAvailablityDTO;
import com.travelbooking.dto.response.TransportResponseDTO;
import com.travelbooking.dto.response.TransportResourceResponseDTO;
import com.travelbooking.entity.*;
import com.travelbooking.repository.TransportRepository;
import com.travelbooking.service.SearchService;


import org.springframework.stereotype.Service;
import com.travelbooking.repository.ResourceRepository;
import com.travelbooking.repository.BookingRepository;

import java.util.*;
import java.time.LocalDateTime;

@Service
public class SearchServiceImpl implements SearchService {

    private final TransportRepository transportRepository;
      private final ResourceRepository resourceRepository;
      private final BookingRepository bookingRespository;

    public SearchServiceImpl(TransportRepository transportRepository, ResourceRepository resourceRepository, BookingRepository bookingRespository)
    {
        this.transportRepository = transportRepository;
        this.resourceRepository = resourceRepository;
        this.bookingRespository = bookingRespository;
    }

      @Override
     public List<TransportResponseDTO> searchTransport(String source, String destination)
      {
         if(source == null || destination == null) {
            throw new IllegalArgumentException("Source and destination cannot be null");
         }

         List<Transport> transports = transportRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
         List<TransportResponseDTO> responseList = new ArrayList<>();

         for(Transport t : transports) {
            responseList.add(new TransportResponseDTO(t));
         }
         return responseList;
  }
   @Override
  public TransportResourceResponseDTO getTransportResourcesWithAvailablity(String transportCode, LocalDateTime journeyDate)
  {
   Transport transport = transportRepository.findByTransportCode(transportCode)
            .orElseThrow(() -> new RuntimeException("Transport not found with code: " + transportCode)); 

            List<Resource> resources = resourceRepository.findByTransport(transport);
            List<Long> bookedResourceIds = bookingRespository.findBookedResourceIds(transport, journeyDate, BookingStatus.CONFIRMED);

            Set<Long> bookedResourceIdSet = new HashSet<>(bookedResourceIds);

            List<ResourceAvailablityDTO> resourceDTOs = resources.stream().map(resource ->
               new ResourceAvailablityDTO(resource.getSection(),
                resource.getResourceNumber(),
                 resource.getResourceClass(),
                  !bookedResourceIdSet.contains(resource.getResourceId())))
               .toList();
            

               return new TransportResourceResponseDTO(transport.getTransportCode(),
                transport.getSource(),
                 transport.getDestination(),
                  transport.getDepartureTime(),
                   transport.getArrivalTime(),
                    resourceDTOs);
  }

}


