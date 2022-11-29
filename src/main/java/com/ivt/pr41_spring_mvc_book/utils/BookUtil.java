/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.utils;

import com.ivt.pr41_spring_mvc_book.entities.BookDetailEntity;
import com.ivt.pr41_spring_mvc_book.entities.BookEntity;
import com.ivt.pr41_spring_mvc_book.entities.CategoryEntity;
import com.ivt.pr41_spring_mvc_book.model.Book;
import com.ivt.pr41_spring_mvc_book.model.BookDetail;
import com.ivt.pr41_spring_mvc_book.model.Category;

/**
 *
 * @author ldanh
 */
public class BookUtil {
    
    public static Book convertBookEntityToBook(BookEntity bookEntity) {
        Book book = new Book();
        if (bookEntity != null) {
            book.setId(bookEntity.getId());
            book.setName(bookEntity.getName());
            book.setAuthor(bookEntity.getAuthor());
            
            if (bookEntity.getCategory() != null) {
                Category category = new Category();
                category.setId(bookEntity.getCategory().getId());
                category.setName(bookEntity.getCategory().getName());
                book.setCategory(category);
            }
            
            if (bookEntity.getBookDetail() != null) {
                BookDetail bd = new BookDetail();
                bd.setId(bookEntity.getBookDetail().getId());
                bd.setIsbn(bookEntity.getBookDetail().getIsbn());
                bd.setNumberOfPage(bookEntity.getBookDetail().getNumberOfPage());
                bd.setPrice(bookEntity.getBookDetail().getPrice());
                bd.setDescription(bookEntity.getBookDetail().getDescription());
                bd.setPublishDate(DateUtils.convertDateToString(bookEntity.getBookDetail().getPublishDate()));
                
                book.setBookDetail(bd);
            }
        }
        return book;
    }
    
    public static BookEntity convertBookToBookEntity(Book book) {
        if (book != null) {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setId(book.getId());
            bookEntity.setName(book.getName());
            bookEntity.setAuthor(book.getAuthor());
            
            if (book.getCategory() != null) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setId(book.getCategory().getId());
                bookEntity.setCategory(categoryEntity);
            }
            
            if (book.getBookDetail() != null) {
                BookDetailEntity bde = new BookDetailEntity();
                bde.setId(book.getBookDetail().getId());
                bde.setDescription(book.getBookDetail().getDescription());
                bde.setIsbn(book.getBookDetail().getIsbn());
                bde.setNumberOfPage(book.getBookDetail().getNumberOfPage());
                bde.setPrice(book.getBookDetail().getPrice());
                bde.setPublishDate(DateUtils.convertStringToDate(book.getBookDetail().getPublishDate()));
                
                bde.setBook(bookEntity);
                bookEntity.setBookDetail(bde);
            }
            return bookEntity;
        }
        return null;
    }
}
