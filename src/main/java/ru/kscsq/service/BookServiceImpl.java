package ru.kscsq.service;


import ru.kscsq.dao.BookDao;
import ru.kscsq.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public void create(Book book) {
        bookDao.create(book);
    }

    @Transactional
    public void read(Book book) {
        bookDao.read(book);
    }

    @Transactional
    public void update(Book book) {
        bookDao.update(book);
    }

    @Transactional
    public void delete(int id) {
        bookDao.delete(id);
    }

    @Transactional
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    @Transactional
    public List<Book> listBooks() {
        return bookDao.listBooks();
    }

    @Transactional
    public List<Book> listSearchedBooks(String searchText) {
        return bookDao.listSearchedBooks(searchText);
    }
}
