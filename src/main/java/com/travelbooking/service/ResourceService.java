package com.travelbooking.service;
import com.travelbooking.dto.request.ResourceRequestDTO;
import com.travelbooking.dto.response.ResourceResponseDTO;

public interface ResourceService {

    ResourceResponseDTO createResource(ResourceRequestDTO resourceRequest);

}
