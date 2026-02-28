package com.travelbooking.entity;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "transport")
public class Transport {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "transport_id")
     private Long transportId;

     @Enumerated(EnumType.STRING)
     @JdbcTypeCode(SqlTypes.NAMED_ENUM)
     @Column(name = "transport_type", columnDefinition = "transport_type_enum", nullable = false)
     private TransportType transportType;

     @Column(name = "transport_code", nullable = false, unique = true)
     private String transportCode;

     @Column(name = "source")
     private String source;
    
      @Column(name = "destination")
    private String destination;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

      public Transport() {}

    // Getters & Setters

    public Long getTransportId() {
        return transportId;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getTransportCode() {
        return transportCode;
    }

    public void setTransportCode(String transportCode) {
        this.transportCode = transportCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

}
