package io.aspect.springbootdemo.web;

import io.aspect.springbootdemo.domain.Book;
import io.aspect.springbootdemo.dto.BookDTO;
import io.aspect.springbootdemo.exception.InvalidRequestException;
import io.aspect.springbootdemo.exception.NotFoundException;
import io.aspect.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class BookApp {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getAll(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        // return bookService.findAll();
        final Page<Book> bookPage = bookService.findAllByPage(pageable);
        if (bookPage.isEmpty()) {
            throw new NotFoundException("Books Not Found");
        }


        return new ResponseEntity<>(bookPage, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> post(@Valid @RequestBody final BookDTO bookDTO,
                                     final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid parameter", bindingResult);
        }
        return new ResponseEntity<>(bookService.save(bookDTO.toBook()), HttpStatus.CREATED);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getOne(@PathVariable final long id) {
        final Book book = bookService.findOne(id);
        if (book == null) {
            throw new NotFoundException(String.format("book by id %d is not found", id));
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> update(@PathVariable final long id,
                                       @Valid @RequestBody final BookDTO bookDTO,
                                       final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid parameter", bindingResult);
        }

        final Book book0 = bookService.findOne(id);

        if (book0 == null) {
            throw new NotFoundException(String.format("book by id %d is not found", id));
        }

        bookDTO.toBook(book0);

        return new ResponseEntity<>(bookService.save(book0), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable final long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/books/by")
    public int findBy(@RequestParam final int id, @RequestParam final int status) {
        return bookService.updateByJPQL(id, status);
    }
}
