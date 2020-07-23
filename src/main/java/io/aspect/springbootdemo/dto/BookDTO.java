package io.aspect.springbootdemo.dto;

import io.aspect.springbootdemo.domain.Book;
import io.aspect.springbootdemo.util.CustomBeanUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDTO {
    @NotBlank
    private String name;

    @Length(min = 2)
    private String author;

    @Length(max = 20)
    private String description;

    @NotNull
    private int status;

    public void toBook(final Book book) {
        new BookConvert().convert(this, book);
    }

    public Book toBook() {
        return new BookConvert().convert(this);
    }

    private static class BookConvert implements Convert<BookDTO, Book> {
        @Override
        public Book convert(BookDTO bookDTO) {
            final Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            return book;
        }

        @Override
        public Book convert(BookDTO bookDTO, Book book) {
            BeanUtils.copyProperties(bookDTO, book, CustomBeanUtils.getNullPropertyNames(bookDTO));
            return book;
        }
    }
}
