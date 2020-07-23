package io.aspect.springbootdemo.service;

import io.aspect.springbootdemo.domain.Book;
import io.aspect.springbootdemo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    /**
     * select * from book
     * @return book list
     */
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllByPage(final Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * create a new book
     * @param book the book to save
     * @return saved book
     */
    @Override
    public Book save(final Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findOne(final long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(final long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findByAuthor(final String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByAuthorAndStatus(final String author, final int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    @Override
    public List<Book> findByDescriptionEndsWith(final String suffix) {
        return bookRepository.findByDescriptionEndsWith(suffix);
    }

    @Override
    public List<Book> findByJPQL(final int length) {
        return bookRepository.findByJPQL(length);
    }

    @Override
    public int updateByJPQL(final int id, final int status) {
        return bookRepository.updateByJPQL(id, status);
    }
}
