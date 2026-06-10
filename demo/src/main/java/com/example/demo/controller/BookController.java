package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.user.BookResponse;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {

    BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable Integer id) {
        BookResponse response = bookService.getBookById(id);
        ApiResponse<BookResponse> body = ApiResponse.<BookResponse>builder()
                .success(true)
                .message("Get book details successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<BookResponse>>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<BookResponse> response = bookService.getAllBooks(page, size);
        ApiResponse<PageResponse<BookResponse>> body = ApiResponse.<PageResponse<BookResponse>>builder()
                .success(true)
                .message("Get all books successfully")
                .data(response)
                .build();
        return ResponseEntity.ok(body);
    }
}
