package com.travelbooking.dto.response;
import java.time.LocalDateTime;

import com.travelbooking.entity.BookingStatus;

public class BookingResponseDTO {

    private Long bookingId;
    private String transportCode;
    private String section;
    private String resourceNumber;
    private LocalDateTime journeyDate;
    private BookingStatus status;

    public BookingResponseDTO(Long bookingId, String transportCode, String section, String resourceNumber, LocalDateTime journeyDate, BookingStatus status) {
        this.bookingId = bookingId;
        this.transportCode = transportCode;
        this.section = section;
        this.resourceNumber = resourceNumber;
        this.journeyDate = journeyDate;
        this.status = status;
    }

    public Long getBookingId() {
        return bookingId;
    }
    public String getTransportCode() {
        return transportCode;
    }
    public String getSection() {
        return section;
    }
    public String getResourceNumber() {
        return resourceNumber;
    }
    public LocalDateTime getJourneyDate() {
        return journeyDate;
    }
    public BookingStatus getStatus() {
        return status;
    }

}
