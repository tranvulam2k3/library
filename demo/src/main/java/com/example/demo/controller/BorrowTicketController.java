package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.borrow_ticket.BorrowTicketResponse;
import com.example.demo.service.borrow_ticket.BorrowTicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrow-tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowTicketController {

    BorrowTicketService borrowTicketService;

    // Lấy toàn bộ phiếu mượn có trong db (danh sachs phân trang)
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BorrowTicketResponse>>> getAllBorrowTickets (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PageResponse<BorrowTicketResponse> response = borrowTicketService.getAllBorrowTickets(page, size);
        ApiResponse<PageResponse<BorrowTicketResponse>> body = ApiResponse.<PageResponse<BorrowTicketResponse>>builder()
                .success(true)
                .message("Get all borrow tickets successfully")
                .data(response)
                .build();

        return ResponseEntity.ok(body);
    }
}
