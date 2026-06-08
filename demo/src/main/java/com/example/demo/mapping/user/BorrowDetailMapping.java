package com.example.demo.mapping.user;


import com.example.demo.dto.user.BorrowDetailResponse;
import com.example.demo.entity.BorrowDetail;
import org.springframework.stereotype.Component;

@Component
public class BorrowDetailMapping {
    public BorrowDetailResponse toResponse(BorrowDetail borrowDetail) {
        return BorrowDetailResponse.builder()
                .borrowDetailId(borrowDetail.getBorrowDetailId())
                .ticketId(borrowDetail.getBorrowTicket().getTicketId())
                .bookId(borrowDetail.getBook().getBookId())
                .quantity(borrowDetail.getQuantity())
                .status(borrowDetail.getStatus())
                .fineAmount(borrowDetail.getFineAmount())
                .build();
    }
}
