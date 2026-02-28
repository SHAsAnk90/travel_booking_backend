package com.travelbooking.dto.response;
import java.time.LocalDateTime;

public class ErrorResponseDTO {

    private boolean success;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;

    public ErrorResponseDTO(String errorCode, String errorMessage)
    {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }
    public boolean isSuccess()
    {
        return success;
    }
    public String getErrorCode()
    {
        return errorCode;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

}
