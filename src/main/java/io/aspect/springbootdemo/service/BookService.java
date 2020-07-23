package io.aspect.springbootdemo.service;

import io.aspect.springbootdemo.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Page<Book> findAllByPage(Pageable pageable);

    Book save(Book book);

    Book findOne(long id);

    void delete(long id);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionEndsWith(String suffix);

    List<Book> findByJPQL(int length);

    int updateByJPQL(int id, int status);
}
