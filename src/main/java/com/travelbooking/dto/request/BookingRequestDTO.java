package com.travelbooking.dto.request;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingRequestDTO{

    private String transportCode;
    private Long resourceId;
    private LocalDateTime journeyDate;
    private Long UserId;
    private BigDecimal amount;

    public Long getUserId() {
        return UserId;
    }
    public Long getResourceId() {
        return resourceId;
    }
    public LocalDateTime getJourneyDate() {
        return journeyDate;
    }
    public String getTransportCode() {
        return transportCode;
    }
    public BigDecimal getAmount() {
        return amount;
    }


}
