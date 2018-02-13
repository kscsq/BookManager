package ru.kscsq.service;

import ru.kscsq.model.Book;
import java.util.List;

public interface BookService {
    void create(Book book);

    void read(Book book);

    void update(Book book);

    void delete(int id);

    Book getBookById(int id);

    List<Book> listBooks();

    List<Book> listSearchedBooks(String searchText);
}
