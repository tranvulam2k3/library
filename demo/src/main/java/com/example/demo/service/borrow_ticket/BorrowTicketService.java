package com.example.demo.service.borrow_ticket;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.borrow_ticket.BorrowTicketResponse;

public interface BorrowTicketService {
    PageResponse<BorrowTicketResponse> getAllBorrowTickets(int page, int size);
}
