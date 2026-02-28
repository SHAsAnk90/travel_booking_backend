package com.travelbooking.service.impl;
import com.travelbooking.dto.request.ResourceRequestDTO;
import com.travelbooking.dto.response.ResourceResponseDTO;
import com.travelbooking.entity.Resource;
import com.travelbooking.entity.Transport;
import com.travelbooking.service.ResourceService;
import org.springframework.stereotype.Service;
import com.travelbooking.repository.ResourceRepository;
import com.travelbooking.repository.TransportRepository;


@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final TransportRepository transportRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository, TransportRepository transportRepository) {
        this.resourceRepository = resourceRepository;
        this.transportRepository = transportRepository;
    }

    @Override
    public ResourceResponseDTO createResource(ResourceRequestDTO resourceRequest) {
        Transport transport = transportRepository.findById(resourceRequest.getTransportId())
                .orElseThrow(() -> new IllegalArgumentException("Transport not found"));

        Resource resource = new Resource();
        resource.setTransport(transport);
        resource.setSection(resourceRequest.getSection());
        resource.setResourceNumber(resourceRequest.getResourceNumber());
        resource.setResourceClass(resourceRequest.getResourceClass());

        Resource savedResource = resourceRepository.save(resource);
        return new ResourceResponseDTO(savedResource.getResourceId(), savedResource.getTransport().getTransportId(),
                savedResource.getSection(), savedResource.getResourceNumber(),
                savedResource.getResourceClass(), savedResource.getCreatedAt());
    }

}
