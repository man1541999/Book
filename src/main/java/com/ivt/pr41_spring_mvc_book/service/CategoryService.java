/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.service;

import com.ivt.pr41_spring_mvc_book.entities.CategoryEntity;
import com.ivt.pr41_spring_mvc_book.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author ldanh
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getCategories() {
        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();
        if (!CollectionUtils.isEmpty(categories)) {
            return categories;
        }
        return new ArrayList<>();
    }
}
