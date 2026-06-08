package com.example.demo.repository;

import com.example.demo.entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowTicketRepository extends JpaRepository<BorrowTicket, Long>{

}
