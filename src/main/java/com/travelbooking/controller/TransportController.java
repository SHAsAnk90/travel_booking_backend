package com.travelbooking.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.travelbooking.service.TransportService;

import com.travelbooking.service.SearchService;

import com.travelbooking.dto.request.TransportRequestDTO;
import com.travelbooking.dto.response.TransportResponseDTO;
import com.travelbooking.dto.response.TransportResourceResponseDTO;
import java.time.LocalDateTime;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/transports")
public class TransportController {

    private final TransportService transportService;
    private final SearchService searchService;


    public TransportController(TransportService transportService, SearchService searchService) {
        this.transportService = transportService;
        this.searchService = searchService;
    }

    @PostMapping
    public ResponseEntity<TransportResponseDTO> createTransport(@RequestBody TransportRequestDTO transportRequest) {
        TransportResponseDTO response = transportService.createTransport(transportRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransportResponseDTO>> getAllTransport() {
        return ResponseEntity.ok(transportService.getAllTransport());
    }

        @GetMapping("/search")
        public ResponseEntity<List<TransportResponseDTO>> searchTransport(@RequestParam String source, @RequestParam String destination) {
            List<TransportResponseDTO> response = searchService.searchTransport(source, destination);
            return ResponseEntity.ok(response);
        }
    
        @GetMapping("/{transportCode}/resources")
        public ResponseEntity<TransportResourceResponseDTO> getTransportResources(@PathVariable String transportCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime journeyDate)
            {
                return ResponseEntity.ok(searchService.getTransportResourcesWithAvailablity(transportCode, journeyDate));
            }
        


}
