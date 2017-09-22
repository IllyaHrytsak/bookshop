package ua.training.bookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.bookshop.model.Book;
import ua.training.bookshop.service.BookService;
import ua.training.bookshop.service.OrdersService;
import ua.training.bookshop.validator.BookValidator;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookValidator bookValidator;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());

        return "books";
    }

    @RequestMapping(value = {"/", "/booklist"}, method = RequestMethod.GET)
    public String bookList(Model model, String repeat) {
        if (repeat != null) {
            model.addAttribute("message", "");
        }
        model.addAttribute("bookList", bookService.listBooks());
        return "booklist";
    }

    @RequestMapping(value = "/add_book", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "add_book";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/edit_book", method = RequestMethod.GET)
    public String editBook(@RequestParam(value = "bookId", defaultValue = "") String bookId, Model model) {
        model.addAttribute("book", bookService.getBookById(Integer.parseInt(bookId)));
        return "edit_book";
    }

    @RequestMapping(value = "/edit_book", method = RequestMethod.POST)
    public String editBook(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "edit_book";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @RequestMapping("/remove_book")
    public String removeBook(@RequestParam(value = "bookId", defaultValue = "") String  bookId){
        this.bookService.removeBook(Integer.parseInt(bookId));
        return "redirect:/books";
    }


}
