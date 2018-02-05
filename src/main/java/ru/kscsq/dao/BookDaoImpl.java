package ru.kscsq.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import ru.kscsq.model.Book;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book " + book.getTitle() + " created");
    }

    public void read(Book book) {
        book.setReadAlready(true);
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book " + book.getTitle() + " is read");
    }

    public void update(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book " + book.getTitle() + " updated");
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);

        if (book != null)
            session.delete(book);

        logger.info("Book " + book.getTitle() + " deleted");
    }


    public Book getBookById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, id);
        logger.info("Book " + book.getTitle() + " loaded");
        return book;
    }

    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();
        for (Book book : bookList)
            logger.info("Book list: " + book);
        return bookList;
    }
}
