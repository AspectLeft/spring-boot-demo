package io.aspect.springbootdemo.web;

import io.aspect.springbootdemo.domain.Book;
import io.aspect.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       final Model model) {
        // final List<Book> books = bookService.findAll();
        Page<Book> bookPage = bookService.findAllByPage(pageable);
        model.addAttribute("bookPage", bookPage);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable final long id, final Model model) {
        Book book = bookService.findOne(id);
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/books/input")
    public String inputPage(final Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }

    /*
        POST -> redirect -> GET
         */
    @PostMapping("/books")
    public String post(final Book book, final RedirectAttributes attributes) {
        final Book book1 = bookService.save(book);
        if (book1 != null) {
            attributes.addFlashAttribute("message", String.format("《%s》已更新", book1.getName()));
        }
        return "redirect:/books";
    }

    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable final long id, final Model model) {
        final Book book = bookService.findOne(id);

        model.addAttribute("book", book);
        return "input";
    }

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id, final RedirectAttributes attributes) {
        bookService.delete(id);
        attributes.addFlashAttribute("message", "Delete completed");

        return "redirect:/books";
    }
}
