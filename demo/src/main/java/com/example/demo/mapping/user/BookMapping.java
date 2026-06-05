package com.example.demo.mapping.user;

import com.example.demo.dto.user.BookResponse;
import com.example.demo.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapping {

    public BookResponse toBookResponse(Book book) {
        if (book == null) {
            return null;
        }

        BookResponse response = new BookResponse();
        response.setBookId(book.getBookId());
        response.setTitle(book.getTitle());
        response.setAuthorName(book.getAuthorName());
        response.setPublisherName(book.getPublisherName());
        response.setIsbn(book.getIsbn());
        response.setPublicationYear(book.getPublicationYear());
        response.setDescription(book.getDescription());
        response.setTotalQuantity(book.getTotalQuantity());
        response.setAvailableQuantity(book.getAvailableQuantity());
        response.setShelfLocation(book.getShelfLocation());
        response.setStatus(book.getStatus());

        if (book.getCategory() != null) {
            response.setCategoryName(book.getCategory().getCategoryName());
        }

        return response;
    }
}
