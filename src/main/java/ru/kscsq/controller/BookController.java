package ru.kscsq.controller;


import org.springframework.web.bind.annotation.*;
import ru.kscsq.model.Book;
import ru.kscsq.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class BookController {
    private BookService bookService;


    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(@RequestParam(value = "printyear", required = false) String printyear, Model model) {
        model.addAttribute("book", new Book());
        if (printyear == null) {
            model.addAttribute("listBooks", bookService.listBooks());
        } else {
            model.addAttribute("listBooks", bookService.listSearchedBooks(printyear));
        }
        return "books";
    }

/*    @RequestMapping(value = "/books/search", method = RequestMethod.POST)
    public String searchBooks(@RequestParam String printyear) {

        this.bookService.listSearchedBooks(printyear);

        return "redirect:/books";
    }*/

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getId() == 0) {
            this.bookService.create(book);
        } else {
            this.bookService.update(book);
        }
        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.delete(id);

        return "redirect:/books";
    }

    @RequestMapping("/read/{id}")
    public String readBook(@PathVariable("id") int id) {
        Book b = this.bookService.getBookById(id);
        b.setReadAlready(true);
        this.bookService.update(b);

        return "redirect:/books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

    @RequestMapping(value = "books", method = RequestMethod.POST)
    public String readBook(@ModelAttribute("book") Book book) {
        if (!book.isReadAlready()) {
            book.setReadAlready(true);
        }


        return "redirect:/books";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        return "bookdata";
    }
}
