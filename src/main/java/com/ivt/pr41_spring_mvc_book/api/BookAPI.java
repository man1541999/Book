/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.api;

import com.ivt.pr41_spring_mvc_book.entities.BookEntity;
import com.ivt.pr41_spring_mvc_book.model.Book;
import com.ivt.pr41_spring_mvc_book.service.BookService;
import com.ivt.pr41_spring_mvc_book.utils.BookUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author ldanh
 */
@RestController
@RequestMapping("/api")
public class BookAPI {
    
    @Autowired
    private BookService bookService;
    
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks() {
        List<BookEntity> bookEntities = bookService.getBooks();
        if (!CollectionUtils.isEmpty(bookEntities)) {
            List<Book> books = new ArrayList<>();
            for (BookEntity bookEntity : bookEntities) {
                books.add(BookUtil.convertBookEntityToBook(bookEntity));
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable("bookId") long id) {
        BookEntity bookEntity = bookService.findBookById(id);
        if (bookEntity.getId() > 0) {
            return new ResponseEntity<>(BookUtil.convertBookEntityToBook(bookEntity),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(
            @RequestBody Book book,
            UriComponentsBuilder builder) {
        BookEntity bookEntity = BookUtil.convertBookToBookEntity(book);
        if (bookEntity != null) {
            bookService.save(bookEntity);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/api//book/{bookId}")
                    .buildAndExpand(bookEntity.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBook(
            @RequestBody Book book,
            UriComponentsBuilder builder) {
        BookEntity bookEntity = BookUtil.convertBookToBookEntity(book);
        if (bookEntity != null) {
            bookService.save(bookEntity);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/api//book/{bookId}")
                    .buildAndExpand(bookEntity.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<?> searchBook(
            @ModelAttribute("searchValue") String searchValue) {
        
        List<BookEntity> bookEntities = bookService.searchBook(searchValue);
        if (!CollectionUtils.isEmpty(bookEntities)) {
            List<Book> books = new ArrayList<>();
            for (BookEntity bookEntity : bookEntities) {
                books.add(BookUtil.convertBookEntityToBook(bookEntity));
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
