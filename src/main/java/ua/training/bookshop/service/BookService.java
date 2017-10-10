package ua.training.bookshop.service;


import ua.training.bookshop.model.Book;

import java.util.List;

/**
 * BookService is responsible for adding, updating, removing and other features.
 * @author Illya Hrytsak
 */
public interface BookService {

    /**
     * Method adds book to the DB.
     * @param book New book
     */
    void addBook(Book book);

    /**
     * Method updates book in the DB.
     * @param book Book with updated information
     */
    void updateBook(Book book);

    /**
     * Method removes book from the DB.
     * @param id Id of the book.
     */
    void removeBook(int id);

    /**
     * Method finds book in the DB by ID.
     * @param id Id of the book
     * @return Found book
     */
    Book getBookById(int id);

    /**
     * Method just returns list of all books in the DB.
     * @return List of books
     */
    List<Book> listBooks();


}
