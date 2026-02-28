package com.travelbooking.dto.request;
import java.time.LocalDateTime;

public class TrainSearchRequestDTO {
    private String source;
    private String destination;
    private LocalDateTime Date;

    public String getSource()
    {
        return source;
    }
    public String getDestination()
    {
        return destination;
    }
    public LocalDateTime getDate()
    {
        return Date;
    }


}
