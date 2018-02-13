package ru.kscsq.dao;


import ru.kscsq.model.Book;
import java.util.List;

public interface BookDao {
    void create(Book book);

    void read(Book book);

    void update(Book book);

    void delete(int id);

    Book getBookById(int id);

    List<Book> listBooks();

    List<Book> listSearchedBooks(String searchText);
}
