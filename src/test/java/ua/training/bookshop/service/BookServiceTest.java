package ua.training.bookshop.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ua.training.bookshop.AbstractTestConfig;
import ua.training.bookshop.dao.BookDao;
import ua.training.bookshop.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookServiceTest extends AbstractTestConfig {

    private static final int BOOK_ID = 1;

    @InjectMocks
    @Autowired
    private BookService bookService;

    @Mock
    private BookDao bookDao;

    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(this.
                <BookService>getTargetObject(bookService), "bookDao", bookDao);
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setId(BOOK_ID);
        Mockito.doReturn(book).when(bookDao).getBookById(BOOK_ID);

        Book result = bookService.getBookById(BOOK_ID);

        Assert.assertEquals(book, result);
    }

    @Test
    public void testListBooks() {
        List<Book> bookList = Collections.singletonList(new Book());
        Mockito.doReturn(bookList).when(bookDao).listBooks();

        Assert.assertFalse(bookService.listBooks().isEmpty());

        Mockito.doReturn(new ArrayList<>()).when(bookDao).listBooks();

        Assert.assertTrue(bookService.listBooks().isEmpty());
    }


}
