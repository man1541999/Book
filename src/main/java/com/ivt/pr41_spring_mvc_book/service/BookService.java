/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.service;

import com.ivt.pr41_spring_mvc_book.entities.BookEntity;
import com.ivt.pr41_spring_mvc_book.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ldanh
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getBooks() {
        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        if (!CollectionUtils.isEmpty(books)) {
            return books;
        }
        return new ArrayList<>();
    }

    public void save(BookEntity book) {
        book.getBookDetail().setBook(book);
        bookRepository.save(book);
    }

    public BookEntity findBookById(long id) {
        Optional<BookEntity> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            return bookOpt.get();
        }
        return new BookEntity();
    }

    public boolean deleteBook(long id) {
        bookRepository.deleteById(id);
        return bookRepository.existsById(id);
    }

    public List<BookEntity> searchBook(String searchValue) {
        List<BookEntity> books = 
                bookRepository.findByNameContainingOrAuthorContaining(searchValue, searchValue);

//        List<BookEntity> books
//                = bookRepository.findBookByCateAndPriceJPQL(searchValue, 150000);
        if (!CollectionUtils.isEmpty(books)) {
            return books;
        }
        return new ArrayList<>();
    }
}
