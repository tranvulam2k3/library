package com.example.demo.service.borrow_ticket;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.borrow_ticket.BorrowTicketResponse;
import com.example.demo.entity.BorrowTicket;
import com.example.demo.mapping.borrow_ticket.BorrowTicketMapping;
import com.example.demo.repository.BorrowTicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BorrowTicketServiceImpl implements BorrowTicketService{

    BorrowTicketRepository borrowTicketRepository;
    BorrowTicketMapping borrowTicketMapping;

    @Override
    public PageResponse<BorrowTicketResponse> getAllBorrowTickets(int page, int size){

        Pageable pageable = PageRequest.of(page, size);

        Page<BorrowTicket> borrowTicketPage = borrowTicketRepository.findAll(pageable);

        List<BorrowTicketResponse> responses = new ArrayList<>();
        for (BorrowTicket borrowTicket : borrowTicketPage.getContent()) {
            responses.add(borrowTicketMapping.toBorrowTicketResponse(borrowTicket));
        }

        PageResponse<BorrowTicketResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(responses);
        pageResponse.setPage(borrowTicketPage.getNumber());
        pageResponse.setSize(borrowTicketPage.getSize());
        pageResponse.setTotalItems(borrowTicketPage.getTotalElements());
        pageResponse.setTotalPages(borrowTicketPage.getTotalPages());

        return pageResponse;
    }
}
