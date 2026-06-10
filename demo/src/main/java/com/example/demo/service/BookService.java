package com.example.demo.service;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.user.BookResponse;
import com.example.demo.entity.Book;
import com.example.demo.enums.ErrorCode;
import com.example.demo.exception.AppException;
import com.example.demo.mapping.user.BookMapping;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {

    BookRepository bookRepository;
    BookMapping bookMapping;

    public BookResponse getBookById(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            throw new AppException(ErrorCode.BOOK_NOT_FOUND, "Book not found with ID: " + id);
        }
        Book book = optionalBook.get();
        return bookMapping.toBookResponse(book);
    }

    public PageResponse<BookResponse> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookRepository.findAll(pageable);

        List<BookResponse> responses = new ArrayList<>();
        for (Book book : bookPage.getContent()) {
            responses.add(bookMapping.toBookResponse(book));
        }

        PageResponse<BookResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(responses);
        pageResponse.setPage(bookPage.getNumber());
        pageResponse.setSize(bookPage.getSize());
        pageResponse.setTotalItems(bookPage.getTotalElements());
        pageResponse.setTotalPages(bookPage.getTotalPages());

        return pageResponse;
    }
}
