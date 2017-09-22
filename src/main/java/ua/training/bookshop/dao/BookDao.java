package ua.training.bookshop.dao;



import ua.training.bookshop.model.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(Integer id);

    Book getBookById(Integer id);

    List<Book> listBooks();
}
