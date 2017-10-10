package ua.training.bookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.bookshop.model.Book;
import ua.training.bookshop.service.BookService;
import ua.training.bookshop.validator.BookValidator;

import java.util.List;

/**
 * Controller for book requests
 * @author Illya Hrytsak
 */
@Controller
public class BookController {

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.BookService}
     */
    @Autowired
    private BookService bookService;

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.validator.BookValidator}
     */
    @Autowired
    private BookValidator bookValidator;

    /**
     * Constant for start page
     */
    private static final int START_PAGE = 0;

    /**
     * Constant for number of max books on the books page
     */
    private static final int BOOKS_PAGE_SIZE = 6;

    /**
     * Constant for number of max books on the booklist page
     */
    private static final int BOOKLIST_PAGE_SIZE = 3;

    /**
     * Method listens requests for books page
     * @param page
     * @param model
     * @return View
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(@RequestParam(required = false) Integer page,
                            Model model){
        List<Book> bookList = bookService.listBooks();
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(bookList);
        pagedListHolder.setPageSize(BOOKS_PAGE_SIZE);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
        model.addAttribute("page", page);
        if(page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(START_PAGE);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }

        return "books";
    }

    /**
     * Method listens requests for booklist page
     * @param page
     * @param model
     * @param repeat
     * @return
     */
    @RequestMapping(value = {"/", "/booklist"}, method = RequestMethod.GET)
    public String bookList(@RequestParam(required = false) Integer page,
                           Model model, String repeat) {
        if (repeat != null) {
            model.addAttribute("message", "");
        }
        List<Book> bookList = bookService.listBooks();
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<>(bookList);
        pagedListHolder.setPageSize(BOOKLIST_PAGE_SIZE);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
        model.addAttribute("page", page);
        if(page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(START_PAGE);
            model.addAttribute("bookList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("bookList", pagedListHolder.getPageList());
        }

        return "booklist";
    }

    /**
     * Method listens GET requests for add_book page
     * @param model
     * @return View
     */
    @RequestMapping(value = "/add_book", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    /**
     * Method listens POST requests for add_book page
     * @param book
     * @param bindingResult
     * @return Redirect to another view
     */
    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "add_book";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }

    /**
     * Method listens GET requests for edit_book page
     * @param bookId
     * @param model
     * @return View
     */
    @RequestMapping(value = "/edit_book", method = RequestMethod.GET)
    public String editBook(@RequestParam(value = "bookId", defaultValue = "") String bookId, Model model) {
        model.addAttribute("book", bookService.getBookById(Integer.parseInt(bookId)));
        return "edit_book";
    }

    /**
     * Method listens POST requests for edit_book page
     * @param book
     * @param bindingResult
     * @return Redirect to another view
     */
    @RequestMapping(value = "/edit_book", method = RequestMethod.POST)
    public String editBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "edit_book";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }

    /**
     * Method listens POST requests for removing book
     * @param bookId
     * @return Redirect to another view
     */
    @RequestMapping("/remove_book")
    public String removeBook(@RequestParam(value = "bookId", defaultValue = "") String  bookId){
        this.bookService.removeBook(Integer.parseInt(bookId));
        return "redirect:/books";
    }

}
