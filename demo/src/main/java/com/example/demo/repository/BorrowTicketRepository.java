package com.example.demo.repository;

import com.example.demo.entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowTicketRepository
        extends JpaRepository<BorrowTicket, Integer> {

    List<BorrowTicket> findByReaderUserId(Integer userId);

    List<BorrowTicket> findByStatus(String status);

    List<BorrowTicket> findByDueDateBefore(LocalDate dueDate);
}
