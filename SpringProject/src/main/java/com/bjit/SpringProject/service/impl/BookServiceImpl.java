package com.bjit.SpringProject.service.impl;

import com.bjit.SpringProject.entity.BookEntity;
import com.bjit.SpringProject.model.BookRequestModel;
import com.bjit.SpringProject.repository.BookRepository;
import com.bjit.SpringProject.service.BookService;
import com.bjit.SpringProject.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<Object> getBook(Long id) {
        Optional<BookEntity> book;
        book = bookRepository.findById(id);
        if (book.isEmpty()){
            return new ResponseEntity<>("No book found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            BookEntity bookEntity = BookEntity.builder()
                    .bookId(book.get().getBookId())
                    .title(book.get().getTitle())
                    .author(book.get().getAuthor())
                    .genre(book.get().getGenre())
                    .price(book.get().getPrice())
                    .build();
            return new ResponseEntity<>(bookEntity, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Iterable<BookEntity>> getBookByAuthor(String author) {
        Iterable<BookEntity> books = bookRepository.findAll();
        List<BookEntity> b = new ArrayList<BookEntity>();
        for(BookEntity book:books) {
            if(book.getAuthor().equals(author)){
                b.add(book);
            }
        }
        return new ResponseEntity<>(b, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Iterable<BookEntity>> getAllBooks() {
        Iterable<BookEntity> books = bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> createBook(BookRequestModel requestModel) {
        BookEntity book = BookEntity.builder()
                .title(requestModel.getTitle())
                .genre(requestModel.getGenre())
                .price(requestModel.getPrice())
                .author(requestModel.getAuthor())
                .build();

        BookEntity savedBook = bookRepository.save(book);

        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateBook(Long id, BookRequestModel requestModel) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()){
            return new ResponseEntity<>("No book found with this id!", HttpStatus.NOT_FOUND);
        }
        else {
            book.get().setTitle(requestModel.getTitle());
            book.get().setAuthor(requestModel.getAuthor());
            book.get().setGenre(requestModel.getGenre());
            book.get().setPrice(requestModel.getPrice());

            BookEntity savedBook = bookRepository.save(book.get());

            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> deleteBook(Long id){
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isEmpty()){
            return new ResponseEntity<>("No book found with this id!", HttpStatus.NOT_FOUND);
        }
        else{
            bookRepository.deleteById(id);
            return new ResponseEntity<>("Book Deleted!", HttpStatus.OK);
        }
    }

}
