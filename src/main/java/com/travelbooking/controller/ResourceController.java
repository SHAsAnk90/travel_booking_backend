package com.travelbooking.controller;
import com.travelbooking.dto.request.ResourceRequestDTO;
import com.travelbooking.dto.response.ResourceResponseDTO;
import com.travelbooking.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService)
    {
        this.resourceService = resourceService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResourceResponseDTO> createResource(@RequestBody ResourceRequestDTO request)
    {
        ResourceResponseDTO response = resourceService.createResource(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/test")
    public String test() {
        return "Resource Controller is working!";
    }


}
