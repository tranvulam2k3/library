package com.example.demo.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class BorrowDetailResponse {

    private Long borrowDetailId;
    private Long ticketId;
    private Long bookId;
    private Integer quantity;
    private String status;
    private BigDecimal fineAmount;
}