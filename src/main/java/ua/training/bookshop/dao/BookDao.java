package ua.training.bookshop.dao;



import ua.training.bookshop.model.Book;

import java.util.List;

/**
 * BookDao is responsible for working with DB.
 * @author Illya Hrytsak
 */
public interface BookDao {

    /**
     * Method saves book to the DB.
     * @param book New book
     */
    void addBook(Book book);

    /**
     * Method updates book in the DB.
     * @param book Updated book
     */
    void updateBook(Book book);

    /**
     * Method removes book from the DB.
     * @param id Book id
     */
    void removeBook(Integer id);

    /**
     * Method returns found book by id
     * @param id Book id
     * @return Found book
     */
    Book getBookById(Integer id);

    /**
     * Method returns list of all books in the DB.
     * @return List of all books
     */
    List<Book> listBooks();

}
