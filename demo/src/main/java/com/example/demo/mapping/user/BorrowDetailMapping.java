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

    public com.example.demo.dto.borrowDetails.BorrowDetailResponse toBorrowDetailResponse(BorrowDetail borrowDetail) {
        if (borrowDetail == null) {
            return null;
        }

        com.example.demo.dto.borrowDetails.BorrowDetailResponse response = new com.example.demo.dto.borrowDetails.BorrowDetailResponse();

        response.setBorrowDetailId(borrowDetail.getBorrowDetailId());

        if (borrowDetail.getBorrowTicket() != null) {
            response.setBookId(borrowDetail.getBook().getBookId());
            response.setBookTitle(borrowDetail.getBook().getTitle());
        }

        response.setQuantity(borrowDetail.getQuantity());
        response.setReturnDate(borrowDetail.getReturnDate());
        response.setStatus(borrowDetail.getStatus());
        response.setFineAmount(borrowDetail.getFineAmount());
        response.setFineReasone(borrowDetail.getFineReason());
        response.setCreatedAt(borrowDetail.getCreatedAt());
        response.setUpdateAt(borrowDetail.getUpdatedAt());

        return response;
    }
}
