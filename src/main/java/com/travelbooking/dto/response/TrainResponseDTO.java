package com.travelbooking.dto.response;
import java.time.LocalDateTime;



public class TrainResponseDTO {
    private int TrainNumber;
    private String TrainName;
    private String source;
    private String destination;
    private LocalDateTime timestamp;

    public TrainResponseDTO(int TrainNumber, String TrainName, String source, String Destination, LocalDateTime timestamp)
    {
        this.TrainNumber = TrainNumber;
        this.TrainName = TrainName; 
        this.source = source;
        this.destination = Destination;
        this.timestamp = LocalDateTime.now();
    }
    public int getTrainNumber()
    {
        return TrainNumber;
    }
    public String getTrainName()
    {
        return TrainName;
    }
    public String getSource()
    {
        return source;
    }
    public String getDestination()
    {
        return destination;
    }
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

}
