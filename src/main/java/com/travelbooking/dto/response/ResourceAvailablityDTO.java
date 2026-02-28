package com.travelbooking.dto.response;

public class ResourceAvailablityDTO {
      

    private String section;
    private String resourceNumber;
    private String resourceClass;
    private boolean available;

    public ResourceAvailablityDTO(String section, String resourceNumber, String resourceClass, boolean available) {
        this.section = section;
        this.resourceNumber = resourceNumber;
        this.resourceClass = resourceClass;
        this.available = available;
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
    public boolean isAvailable() {
        return available;
    }
}
