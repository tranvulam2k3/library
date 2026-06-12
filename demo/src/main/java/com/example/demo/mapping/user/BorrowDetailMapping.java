package com.example.demo.mapping.user;

import com.example.demo.dto.borrowDetails.BorrowDetailResponse;
import com.example.demo.entity.BorrowDetail;
import org.springframework.stereotype.Component;

@Component
public class BorrowDetailMapping {

    public BorrowDetailResponse toBorrowDetailResponse(BorrowDetail borrowDetail) {
        if (borrowDetail == null) {
            return null;
        }

        BorrowDetailResponse response = new BorrowDetailResponse();

        response.setBorrowDetailId(borrowDetail.getBorrowDetailId());

        if (borrowDetail.getBorrowTicket() != null) {
            response.setTicketId(borrowDetail.getBorrowTicket().getTicketId());
        }

        if (borrowDetail.getBook() != null) {
            response.setBookId(borrowDetail.getBook().getBookId());
            response.setBookTitle(borrowDetail.getBook().getTitle());
        }

        response.setQuantity(borrowDetail.getQuantity());
        response.setReturnDate(borrowDetail.getReturnDate());
        response.setStatus(borrowDetail.getStatus());
        response.setFineAmount(borrowDetail.getFineAmount());
        response.setFineReason(borrowDetail.getFineReason());
        response.setCreatedAt(borrowDetail.getCreatedAt());
        response.setUpdatedAt(borrowDetail.getUpdatedAt());

        return response;
    }
}