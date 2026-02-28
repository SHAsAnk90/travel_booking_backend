package com.travelbooking.dto.request;
import java.time.LocalDateTime;
import com.travelbooking.entity.*;

public class TransportRequestDTO {

    private TransportType transportType;
    private String transportCode;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public TransportType getTransportType() {
        return transportType;
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

}
