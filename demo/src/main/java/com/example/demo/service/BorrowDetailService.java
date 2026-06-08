package com.example.demo.service;

import com.example.demo.dto.user.BorrowDetailResponse;
import com.example.demo.dto.user.CreateBorrowDetailRequest;
import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowDetail;
import com.example.demo.entity.BorrowTicket;
import com.example.demo.mapping.user.BorrowDetailMapping;
import com.example.demo.mapping.user.BorrowDetailMapping;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowDetailRepository;
import com.example.demo.repository.BorrowTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowDetailService {

    private final BorrowDetailRepository borrowDetailRepository;
    private final BorrowTicketRepository borrowTicketRepository;
    private final BookRepository bookRepository;
    private final BorrowDetailMapping borrowDetailMapping;

    public BorrowDetailResponse create(CreateBorrowDetailRequest req) {

        Optional<BorrowTicket> optionalTicket = borrowTicketRepository.findById(req.getTicketId());

        if (optionalTicket.isEmpty()) {
            throw new RuntimeException("Borrow ticket not found");
        }

        Optional<Book> optionalBook = bookRepository.findById(req.getBookId());

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        BorrowTicket ticket = optionalTicket.get();
        Book book = optionalBook.get();

        if (book.getAvailableQuantity() < req.getQuantity()) {
            throw new RuntimeException("Not enough books available");
        }

        BorrowDetail borrowDetail = new BorrowDetail();

        borrowDetail.setBorrowTicket(ticket);
        borrowDetail.setBook(book);
        borrowDetail.setQuantity(req.getQuantity());

        borrowDetail.setStatus("BORROWING");
        borrowDetail.setFineAmount(BigDecimal.ZERO);

        borrowDetail.setCreatedAt(LocalDateTime.now());
        borrowDetail.setUpdatedAt(LocalDateTime.now());

        BorrowDetail savedBorrowDetail =
                borrowDetailRepository.save(borrowDetail);

        book.setAvailableQuantity(
                book.getAvailableQuantity() - req.getQuantity()
        );

        bookRepository.save(book);

        return borrowDetailMapping.toResponse(savedBorrowDetail);
    }
}