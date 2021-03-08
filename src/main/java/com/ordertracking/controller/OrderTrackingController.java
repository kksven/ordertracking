package com.ordertracking.controller;

import com.ordertracking.exception.StorageFileNotFoundException;
import com.ordertracking.services.TrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OrderTrackingController {

    private TrackingService trackingService;

    public OrderTrackingController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @PostMapping("/order/tracking/")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        trackingService.updateStatus(file);
        return file.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
