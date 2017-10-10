package ua.training.bookshop.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.training.bookshop.model.Book;

/**
 * BookValidator is responsible for input information for book.
 * Base implementation of
 * {@link org.springframework.validation.Validator}
 *
 * @author Illya Hrytsak
 */
public class BookValidator implements Validator {

    /**
     * Implementation method from
     * {@link org.springframework.validation.Validator}
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    /**
     * Implementation method from
     * {@link org.springframework.validation.Validator}
     */
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
