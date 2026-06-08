package com.example.demo.repository;

import com.example.demo.entity.BorrowDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowDetailRepository
        extends JpaRepository<BorrowDetail, Integer> {

    List<BorrowDetail> findByStatus(String status);

    List<BorrowDetail> findByTicketTicketId(Integer ticketId);

    List<BorrowDetail> findByBookBookId(Integer bookId);
}