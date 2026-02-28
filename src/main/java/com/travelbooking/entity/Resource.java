package com.travelbooking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "resource", uniqueConstraints = {@UniqueConstraint(columnNames = {"transport_id", "coach_or_room", "resource_number"})})
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id", nullable = false)
    private Transport transport;

    @Column(name = "coach_or_room")
    private String section;

    @Column(name = "resource_number", nullable = false)
    private String resourceNumber;

    @Column(name = "resource_class")
    private String resourceClass;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructors
    public Resource() {}

    // Getters & Setters
    public Long getResourceId() {
        return resourceId;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getResourceNumber() {
        return resourceNumber;
    }

    public void setResourceNumber(String resourceNumber) {
        this.resourceNumber = resourceNumber;
    }

    public String getResourceClass()
    {
        return resourceClass;
    }

    public void setResourceClass(String resourceClass)
    {
        this.resourceClass = resourceClass;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt()
    {
        this.createdAt = LocalDateTime.now();
    }
}
