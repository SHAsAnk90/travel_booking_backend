package com.travelbooking.dto.request;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingRequestDTO{

    private Long userId;
    private Long resourceId;
    private LocalDateTime journeyDate;
    private BigDecimal amount;

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


}
