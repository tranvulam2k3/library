package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.borrowDetails.BorrowDetailResponse;
import com.example.demo.dto.borrowDetails.ReturnBorrowDetailRequest;
import com.example.demo.dto.user.CreateBorrowDetailRequest;
import com.example.demo.service.BorrowDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<com.example.demo.dto.borrowDetails.BorrowDetailResponse>>> getAllBorrowDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageResponse<com.example.demo.dto.borrowDetails.BorrowDetailResponse> response = borrowDetailService.getAllBorrowDetails(page, size);

        ApiResponse<PageResponse<com.example.demo.dto.borrowDetails.BorrowDetailResponse>> body = ApiResponse.<PageResponse<com.example.demo.dto.borrowDetails.BorrowDetailResponse>>builder()
                .success(true)
                .message("Get all borrow details sucessfully")
                .data(response)
                .build();

        return ResponseEntity.ok(body);
    }

    // API - Cập nhật trạng thái trả sách
    @PutMapping("/{id}/return")
    public ResponseEntity<ApiResponse<BorrowDetailResponse>> returnBorrowDetail(
            @PathVariable Long id,
            @RequestBody ReturnBorrowDetailRequest request
    ) {
        BorrowDetailResponse response = borrowDetailService.returnBorrowDetail(id, request);

        ApiResponse<BorrowDetailResponse> body = ApiResponse.<BorrowDetailResponse>builder()
                .success(true)
                .message("Return book successfully")
                .data(response)
                .build();

        return ResponseEntity.ok(body);
    }
}
