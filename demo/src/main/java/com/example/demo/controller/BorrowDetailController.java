package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.user.BorrowDetailResponse;
import com.example.demo.dto.user.CreateBorrowDetailRequest;
import com.example.demo.service.BorrowDetailService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrow-details")
@RequiredArgsConstructor
public class BorrowDetailController {

    private final BorrowDetailService borrowDetailService;

    @PostMapping
    @Operation(summary = "Create borrow detail")
    public ResponseEntity<ApiResponse<BorrowDetailResponse>> create(
            @RequestBody CreateBorrowDetailRequest req) {

        BorrowDetailResponse response =
                borrowDetailService.create(req);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<BorrowDetailResponse>builder()
                        .success(true)
                        .message("Borrow detail created successfully")
                        .data(response)
                        .build());
    }
}
