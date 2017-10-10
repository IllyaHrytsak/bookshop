package ua.training.bookshop.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.service.AccountService;

/**
 * RegisterValidator is responsible for input information for account.
 * Base implementation of
 * {@link org.springframework.validation.Validator}
 *
 * @author Illya Hrytsak
 */
@Component
public class RegisterValidator implements Validator {

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.AccountService}
     */
    @Autowired
    private AccountService accountService;

    /**
     * Constant for email regex
     */
    private static final String emailRegex = "^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";

    /**
     * Constant for phone number regex
     */
    private static final String phoneNumberRegex = "^\\+380[\\d]{9}$";

    /**
     * Implementation method from
     * {@link org.springframework.validation.Validator}
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }

    /**
     * Implementation method from
     * {@link org.springframework.validation.Validator}
     */
    @Override
    public void validate(Object o, Errors errors) {
        Account account = (Account) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (!account.getEmail().matches(emailRegex)) {
            errors.rejectValue("email", "Format.userForm.email");
        }

        if (accountService.findByEmail(account.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (account.getPassword().length() < 8) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "Required");
        if (!account.getConfirmPassword().equals(account.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        if (account.getFirstName().length() < 2) {
            errors.rejectValue("firstName", "Size.userForm.firstName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        if (account.getLastName().length() < 2) {
            errors.rejectValue("lastName", "Size.userForm.lastName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "Required");
        if (!account.getPhoneNumber().matches(phoneNumberRegex)) {
            errors.rejectValue("phoneNumber", "Format.userForm.phoneNumber");
        }

    }
}
