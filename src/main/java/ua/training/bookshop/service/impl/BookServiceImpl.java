package ua.training.bookshop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.bookshop.dao.BookDao;
import ua.training.bookshop.model.Book;
import ua.training.bookshop.service.BookService;

import java.util.List;

/**
 * Base implementation of
 * {@link ua.training.bookshop.service.BookService}
 *
 * @author Illya Hrytsak
 */
@Service
public class BookServiceImpl implements BookService {

    /**
     * Field for injecting realization of {@link ua.training.bookshop.dao.BookDao}
     */
    @Autowired
    private BookDao bookDao;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.BookService}
     * @param book New book
     */
    @Override
    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.BookService}
     * @param book Book with updated information
     */
    @Override
    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.BookService}
     * @param id Id of the book.
     */
    @Override
    @Transactional
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.BookService}
     * @param id Id of the book
     * @return Found book
     */
    @Override
    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.BookService}
     * @return List of books
     */
    @Override
    @Transactional
    public List<Book> listBooks() {
        return this.bookDao.listBooks();
    }

}
