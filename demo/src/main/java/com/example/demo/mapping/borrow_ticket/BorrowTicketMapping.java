package com.example.demo.mapping.borrow_ticket;

import com.example.demo.dto.borrow_ticket.BorrowTicketResponse;
import com.example.demo.entity.BorrowTicket;
import org.springframework.stereotype.Component;

@Component
public class BorrowTicketMapping {

    public BorrowTicketResponse toBorrowTicketResponse(BorrowTicket borrowTicket) {
        if (borrowTicket == null){
            return null;
        }

        BorrowTicketResponse response = new BorrowTicketResponse();
        response.setTicketId(borrowTicket.getTicketId());

        if (borrowTicket.getReader() != null) {
            response.setReaderId(borrowTicket.getReader().getUserId());
        }

        if (borrowTicket.getLibrarian() != null) {
            response.setLibrarianId(borrowTicket.getLibrarian().getUserId());
        }

        response.setBorrowDate(borrowTicket.getBorrowDate());
        response.setDueDate(borrowTicket.getDueDate());
        response.setStatus(borrowTicket.getStatus());
        response.setNote(borrowTicket.getNote());
        response.setCreatedAt(borrowTicket.getCreatedAt());
        response.setUpdatedAt(borrowTicket.getUpdatedAt());

        return response;
    }
}
