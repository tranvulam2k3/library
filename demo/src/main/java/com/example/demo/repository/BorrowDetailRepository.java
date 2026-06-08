package com.example.demo.repository;

import com.example.demo.entity.BorrowDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowDetailRepository
        extends JpaRepository<BorrowDetail, Long> {

    List<BorrowDetail> findByStatus(String status);

    List<BorrowDetail> findByBorrowTicketTicketId(Long ticketId);

    List<BorrowDetail> findByBookBookId(Long bookId);
}