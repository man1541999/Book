/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.repository;

import com.ivt.pr41_spring_mvc_book.entities.BookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ldanh
 */
@Repository
public interface BookRepository
        extends CrudRepository<BookEntity, Long> {

    @Query(value = "select * from book b\n"
            + " inner join category c on b.category_id = c.id\n"
            + " inner join book_detail bd on bd.book_id = b.id\n"
            + " where c.name like ?1 and bd.price <= ?2", nativeQuery = true)
    List<BookEntity> findBookByCateAndPrice(String category, double price);

    @Query("select b from BookEntity b where b.category.name like ?1 "
            + " and b.bookDetail.price <= ?2")
    List<BookEntity> findBookByCateAndPriceJPQL(String category, double price);

    List<BookEntity> findByNameContainingOrAuthorContaining(String name, String author);

   
}
