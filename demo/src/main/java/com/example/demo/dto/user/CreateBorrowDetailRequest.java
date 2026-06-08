package com.example.demo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBorrowDetailRequest {

    private Integer ticketId;

    private Long bookId;

    private Integer quantity;
}
