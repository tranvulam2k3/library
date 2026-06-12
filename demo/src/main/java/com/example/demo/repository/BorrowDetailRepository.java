package com.example.demo.repository;

import com.example.demo.entity.BorrowDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowDetailRepository extends JpaRepository<BorrowDetail, Long> {

    List<BorrowDetail> findByStatus(String status);

    List<BorrowDetail> findByBorrowTicketTicketId(Long ticketId);

    List<BorrowDetail> findByBookBookId(Long bookId);

    @Query("""
            SELECT bd
            FROM BorrowDetail bd
            WHERE bd.status = 'BORROWING'
            AND bd.borrowTicket.dueDate < :today
            """)
    List<BorrowDetail> findOverdueBorrowDetails(@Param("today") LocalDate today);
}