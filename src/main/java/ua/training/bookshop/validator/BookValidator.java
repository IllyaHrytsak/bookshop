package ua.training.bookshop.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.training.bookshop.model.Book;

public class BookValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookTitle", "Required");
        if (book.getBookTitle().length() < 2) {
            errors.rejectValue("bookTitle", "Size.bookTitle");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookAuthor", "Required");
        if (book.getBookAuthor().length() < 2) {
            errors.rejectValue("bookAuthor", "Size.bookAuthor");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookPrice", "Required");
        if (book.getBookPrice() == null || book.getBookPrice() < 1 || book.getBookPrice() > 10000) {
            errors.rejectValue("bookPrice", "Size.bookPrice");
        }
    }
}
