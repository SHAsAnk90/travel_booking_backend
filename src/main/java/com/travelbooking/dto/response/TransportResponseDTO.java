package com.travelbooking.dto.response;
import java.time.LocalDateTime;
import com.travelbooking.entity.*;

public class TransportResponseDTO {

    private Long transportId;
    private TransportType type;
    private String transportcode;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDateTime createdAt;

     public TransportResponseDTO() {}

     public TransportResponseDTO(Transport transport) {
        this.transportId = transport.getTransportId();
        this.type = transport.getTransportType();
        this.transportcode = transport.getTransportCode();
        this.source = transport.getSource();
        this.destination = transport.getDestination();
        this.departureTime = transport.getDepartureTime();
        this.arrivalTime = transport.getArrivalTime();
        this.createdAt = transport.getCreatedAt();
    }

    public Long getTransportId() {
        return transportId;
    }
    public TransportType getTransportType() {
        return type;
    }
    public String getTransportcode() {
        return transportcode;
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
