package com.travelbooking.dto.response;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookingResponseDTO {

    private Long bookingId;
    private Long userId;
    private Long resourceId;
    private LocalDateTime journeyDate;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;

    public BookingResponseDTO(Long bookingId, Long userId, Long resourceId, LocalDateTime journeyDate, BigDecimal amount, String status, LocalDateTime createdAt) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.resourceId = resourceId;
        this.journeyDate = journeyDate;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getBookingId() {
        return bookingId;
    }
    public Long getUserId() {
        return userId;
    }
    public Long getResourceId() {
        return resourceId;
    }
    public LocalDateTime getJourneyDate() {
        return journeyDate;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public String getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
