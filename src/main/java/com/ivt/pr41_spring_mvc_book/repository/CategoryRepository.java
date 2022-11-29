/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.repository;

import com.ivt.pr41_spring_mvc_book.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ldanh
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

}
