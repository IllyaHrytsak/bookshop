package ua.training.bookshop.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.dao.BookDao;
import ua.training.bookshop.model.Book;

import java.util.List;

/**
 * Base implementation of
 * {@link ua.training.bookshop.dao.BookDao}
 *
 * @author Illya Hrytsak
 */
@Repository
public class BookDaoImpl implements BookDao {

    /**
     * Logger for logging class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    /**
     * Field for injecting realization of {@link org.hibernate.SessionFactory}
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.BookDao}
     * @param book New book
     */
    @Override
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        LOGGER.info("Book successfully saved. Book details: " + book);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.BookDao}
     * @param book Updated book
     */
    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        LOGGER.info("Book successfully update. Book details: " + book);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.BookDao}
     * @param id Book id
     */
    @Override
    public void removeBook(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);

        if(book != null){
            session.delete(book);
        }
        LOGGER.info("Book successfully removed. Book details: " + book);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.BookDao}
     * @param id Book id
     * @return Found book
     */
    @Override
    public Book getBookById(Integer id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);
        LOGGER.info("Book successfully loaded. Book details: " + book);

        return book;
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.BookDao}
     * @return List of all books
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Book> listBooks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> bookList = session.createQuery("from Book").list();

        if (bookList == null || bookList.isEmpty()) {
            return null;
        }
        for (Book book : bookList){
            LOGGER.info("Book list: " + book);
        }

        return bookList;
    }


}
