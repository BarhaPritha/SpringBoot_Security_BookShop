package com.bjit.SpringProject.controllers;

import com.bjit.SpringProject.entity.BookEntity;
import com.bjit.SpringProject.model.BookRequestModel;
import com.bjit.SpringProject.repository.BookRepository;
import com.bjit.SpringProject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/id/{bookId}")
    public ResponseEntity<Object> getBook(@PathVariable String bookId) {
        return bookService.getBook(Long.parseLong(bookId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> newBook(@RequestBody BookRequestModel book) {
        return bookService.createBook(book);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable String id, @RequestBody BookRequestModel book) {
        return bookService.updateBook(Long.parseLong(id), book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable String id) {
        return bookService.deleteBook(Long.parseLong(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<BookEntity>> allBook() {
        return bookService.getAllBooks();
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<Iterable<BookEntity>> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }

}
