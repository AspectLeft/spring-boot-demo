package io.aspect.springbootdemo.web;

import io.aspect.springbootdemo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @Controller
@RestController
@RequestMapping("/v0")
public class HelloController {

    // @RequestMapping(value = "/say", method = RequestMethod.GET)
    @GetMapping("/say")
    public String hello() {
        return "hello Spring Boot";
    }

    @GetMapping("/books")
    // @ResponseBody
    public Object getAll(@RequestParam("page") final int page,
                         @RequestParam(value = "size", defaultValue = "10") final int size) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", "name");
        book.put("isbn", "isbn");
        book.put("author", "author");

        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "Harry Potter 2");
        book2.put("isbn", "90142345678");
        book2.put("author", "J. K. Rowling");

        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("size", size);
        pageMap.put("content", contents);

        return pageMap;
    }

    /**
     * Reg Expr: {attrName:RegEx}
     * @param bid
     * @return
     */
    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable(name = "id") final long bid) {

        return null;
    }

    @PostMapping("/books")
    public Object post(@RequestParam("name") final String name, @RequestParam final String author,
                       @RequestParam final String isbn) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);

        return book;
    }
}
