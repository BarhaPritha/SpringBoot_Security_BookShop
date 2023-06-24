package com.bjit.SpringProject.service;

import com.bjit.SpringProject.entity.BookEntity;
import com.bjit.SpringProject.model.BookRequestModel;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<Object> getBook(Long id);
    ResponseEntity<Iterable<BookEntity>> getBookByAuthor(String author);
    ResponseEntity<Iterable<BookEntity>> getAllBooks();
    ResponseEntity<Object> createBook(BookRequestModel requestModel);
    ResponseEntity<Object> updateBook(Long id, BookRequestModel requestModel);
    ResponseEntity<Object> deleteBook(Long id);

}
