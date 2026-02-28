package com.travelbooking.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class TransportResourceResponseDTO {

    private String transportCode;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<ResourceAvailablityDTO> resources;

    public TransportResourceResponseDTO(String transportCode, String source, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, List<ResourceAvailablityDTO> resources) {
        this.transportCode = transportCode;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.resources = resources;
    }

    public String getTransportCode() {
        return transportCode;
    }
    public String getSource() {
        return source;
    }
    public String getDestination() {
        return destination;
    }
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    public List<ResourceAvailablityDTO> getResources() {
        return resources;
    }

}
