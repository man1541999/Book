/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.pr41_spring_mvc_book.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ldanh
 */
@RestController
@RequestMapping("/api")
public class HomeAPI {

    @RequestMapping(value = "/hello-api", method = RequestMethod.GET)
    public Object helloApi() {
        return "Hello API";
    }

    @RequestMapping(value = "/api-request-param", method = RequestMethod.GET)
    public Object apiRequestParam(@RequestParam("name") String name,
            @RequestParam("age") String age) {
        return "Hello " + name + " age " + age;
    }

    @RequestMapping(value = "/api-path-variable/{name}/{age}", method = RequestMethod.GET)
    public Object apiPathVariable(@PathVariable("name") String name,
            @PathVariable("age") String age) {
        return "Hello " + name + " age " + age;
    }
}
