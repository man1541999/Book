/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.controller;

import com.ivt.pr41_spring_mvc_book.entities.BookEntity;
import com.ivt.pr41_spring_mvc_book.service.BookService;
import com.ivt.pr41_spring_mvc_book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ldanh
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(
            @RequestParam(name = "message", required = false) String message,
            @RequestParam(name = "messageType", required = false) String messageType,
            Model model) {

        model.addAttribute("message", message);
        model.addAttribute("messageType", messageType);
        model.addAttribute("books", bookService.getBooks());
        return "home";
    }

    @RequestMapping("/add-book")
    public String addBook(Model model) {
        model.addAttribute("book", new BookEntity());
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("action", "add");
        return "book-form";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String resultBook(@ModelAttribute("book") BookEntity book,
            Model model) {
        bookService.save(book);
        return "redirect:/home";
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") long id,
            Model model) {
        BookEntity book = bookService.findBookById(id);
        if (book.getId() > 0) {
            model.addAttribute("action", "update");
        } else {
            model.addAttribute("action", "add");
        }
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.getCategories());
        return "book-form";
    }

    @RequestMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable("id") long id,
            Model model) {
        if (!bookService.deleteBook(id)) {
            return "redirect:/home?message=Delete success&messageType=success";
        } else {
            model.addAttribute("message", "Delete fail");
            model.addAttribute("messageType", "fail");
            model.addAttribute("books", bookService.getBooks());
            return "home";
        }
    }

    @RequestMapping(value = "/search-book", method = RequestMethod.POST)
    public String searchBook(
            @ModelAttribute("searchValue") String searchValue,
            Model model) {
        model.addAttribute("bookss", bookService.searchBook(searchValue));
        return "home1";
    }

}
