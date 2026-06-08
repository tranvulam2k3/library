package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.publisher.PublisherRequest;
import com.example.demo.dto.publisher.PublisherResponse;
import com.example.demo.entity.Publisher;
import com.example.demo.service.publisher.PublisherService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api/v1/publishers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PublisherController {
    PublisherService publisherService;
    private final JsonMapper.Builder builder;

    @PostMapping
    public ResponseEntity<ApiResponse<PublisherResponse>> createPublisher(
            @RequestBody
            @Valid
            PublisherRequest request
    ) {
        PublisherResponse response = publisherService.createPublisher(request);
        ApiResponse<PublisherResponse> body = ApiResponse.<PublisherResponse>builder()
                .success(true)
                .message("Publisher created successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherResponse>> getPublisherById(
            @PathVariable
            Integer id
    ) {
        PublisherResponse response = publisherService.getPublisherById(id);
        ApiResponse<PublisherResponse> body = ApiResponse.<PublisherResponse>builder()
                .success(true)
                .message("Get publisher details successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PublisherResponse>>> getAllPublishers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<PublisherResponse> response = publisherService.getAllPublishers(page, size);
        ApiResponse<PageResponse<PublisherResponse>> body = ApiResponse.<PageResponse<PublisherResponse>>builder()
                .success(true)
                .message("Get all publishers successfully")
                .data(response)
                .build();

        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PublisherResponse>> updatePublisher(
            @PathVariable
            Integer id,
            @RequestBody
            @Valid
            PublisherRequest request
    ) {
        PublisherResponse response = publisherService.updatePublisher(id, request);
        ApiResponse<PublisherResponse> body = ApiResponse.<PublisherResponse>builder()
                .success(true)
                .message("Publisher updated successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePublisher(
            @PathVariable
            Integer id
    ) {
        publisherService.deletePublisherById(id);
        ApiResponse<Void> body = ApiResponse.<Void>builder()
                .success(true)
                .message("Publisher deleted successfully")
                .build();
        return ResponseEntity.ok(body);
    }
}
