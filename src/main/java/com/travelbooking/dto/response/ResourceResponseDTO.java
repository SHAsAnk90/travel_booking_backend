package com.travelbooking.dto.response;

import java.time.LocalDateTime;

public class ResourceResponseDTO {

    private Long resourceId;
    private Long transportId;
    private String section;
    private String resourceNumber;
    private String resourceClass;
    private LocalDateTime createdAt;

    public ResourceResponseDTO(Long resourceId, Long transportId, String section, String resourceNumber, String resourceClass, LocalDateTime createdAt) {
        this.resourceId = resourceId;
        this.transportId = transportId;
        this.section = section;
        this.resourceNumber = resourceNumber;
        this.resourceClass = resourceClass;
        this.createdAt = createdAt;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public Long getTransportId() {
        return transportId;
    }

    public String getSection() {
        return section;
    }

    public String getResourceNumber() {
        return resourceNumber;
    }

    public String getResourceClass() {
        return resourceClass;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
