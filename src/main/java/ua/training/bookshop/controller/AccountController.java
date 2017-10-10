package ua.training.bookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Orders;
import ua.training.bookshop.service.AccountService;
import ua.training.bookshop.service.OrdersService;
import ua.training.bookshop.service.SecurityService;
import ua.training.bookshop.validator.RegisterValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for account requests
 * @author Illya Hrytsak
 */
@Controller
public class AccountController {

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.AccountService}
     */
    @Autowired
    private AccountService accountService;

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.SecurityService}
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.OrdersService}
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.validator.RegisterValidator}
     */
    @Autowired
    private RegisterValidator registerValidator;

    /**
     * Constant for phone number regex
     */
    private static final String phoneNumberRegex = "^\\+380[\\d]{9}$";

    /**
     * Constant for card number regex
     */
    private static final String cardNumberRegex = "^[\\d]{4}(\\s?)[\\d]{4}(\\s?)[\\d]{4}(\\s?)[\\d]{4}$";

    /**
     * Constant for CVV regex
     */
    private static final String cvvRegex = "^[0-9]{3}$";

    /**
     * Method listens requests on 403 error
     * @return View
     */
    @RequestMapping(value = "/403")
    public String accessDeniedPage() {
        return "/403";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String welcome(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "");
        }

        if (logout != null) {
            model.addAttribute("message", "");
        }

        return "login";
    }

    /**
     * Method listens GET requests on registration
     * @param model Model
     * @return View
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Account());

        return "registration";
    }

    /**
     * Method listens POST requests on registration
     * @param userForm Attribute for creating account
     * @param bindingResult Binding result
     * @param model Model
     * @return Redirect to another view
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Account userForm, BindingResult bindingResult, Model model) {
        registerValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        accountService.saveAccount(userForm);

        securityService.autoLogin(userForm.getEmail(), userForm.getConfirmPassword());

        return "redirect:/home";
    }

    /**
     * Method listens requests on home page
     * @param model
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param cardNumber
     * @return View
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String accountInfo(Model model, String firstName, String lastName,
                              String phoneNumber, String cardNumber) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (firstName != null) {
            model.addAttribute("firstName", "");
        }

        if (lastName != null) {
            model.addAttribute("lastName", "");
        }

        if (phoneNumber != null) {
            model.addAttribute("phoneNumber", "");
        }

        if (cardNumber != null) {
            model.addAttribute("cardNumber", "");
        }

        model.addAttribute("account", account);
        return "home";
    }

    /**
     * Method listens GET requests on new_first_name page
     * @param model
     * @return View
     */
    @RequestMapping(value = "/home/new_first_name", method = RequestMethod.GET)
    public String editFirstName(Model model) {
        return "new_first_name";
    }

    /**
     * Method listens POST requests on new_first_name page
     * @param request
     * @param model
     * @return Redirect to another view
     */
    @RequestMapping(value = "/home/new_first_name", method = RequestMethod.POST)
    public String editFirstName(HttpServletRequest request, Model model) {
        String newFirstName = request.getParameter("newFirstName");
        String confirmNewFirstName = request.getParameter("confirmNewFirstName");
        if (newFirstName.length() < 2) {
            model.addAttribute("length", "");
            return "new_first_name";
        }

        if (!newFirstName.equals(confirmNewFirstName)) {
            model.addAttribute("noMatches", "");
            return "new_first_name";
        }
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        account.setFirstName(newFirstName);
        accountService.updateAccount(account);
        return "redirect:/home?firstName";
    }

    /**
     * Method listens GET requests on new_last_name page
     * @param model
     * @return View
     */
    @RequestMapping(value = "/home/new_last_name", method = RequestMethod.GET)
    public String editLastName(Model model) {
        return "new_last_name";
    }

    /**
     * Method listens POST requests on new_last_name page
     * @param request
     * @param model
     * @return Redirect to another view
     */
    @RequestMapping(value = "/home/new_last_name", method = RequestMethod.POST)
    public String editLastName(HttpServletRequest request, Model model) {
        String newLastName = request.getParameter("newLastName");
        String confirmNewLastName = request.getParameter("confirmNewLastName");
        if (newLastName.length() < 2) {
            model.addAttribute("length", "Last name must be over 2 characters.");
            return "new_last_name";
        }

        if (!newLastName.equals(confirmNewLastName)) {
            model.addAttribute("noMatches", "Last name doesn't match.");
            return "new_last_name";
        }
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        account.setLastName(newLastName);
        accountService.updateAccount(account);
        return "redirect:/home?lastName";
    }

    /**
     * Method listens GET requests on new_phone_number page
     * @return View
     */
    @RequestMapping(value = "/home/new_phone_number", method = RequestMethod.GET)
    public String editMobilePhone() {
        return "new_phone_number";
    }

    /**
     * Method listens POST requests on new_phone_number page
     * @param request
     * @param model
     * @return Redirect to another view
     */
    @RequestMapping(value = "/home/new_phone_number", method = RequestMethod.POST)
    public String editPhoneNumber(HttpServletRequest request, Model model) {
        String newPhoneNumber = request.getParameter("newPhoneNumber");
        String confirmNewPhoneNumber = request.getParameter("confirmNewPhoneNumber");

        if (!newPhoneNumber.matches(phoneNumberRegex)) {
            model.addAttribute("wrongFormat", "");
            return "new_phone_number";
        }

        if (!newPhoneNumber.equals(confirmNewPhoneNumber)) {
            model.addAttribute("noMatches", "");
            return "new_phone_number";
        }

        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        account.setPhoneNumber(newPhoneNumber);
        accountService.updateAccount(account);
        return "redirect:/home?phoneNumber";
    }

    /**
     * Method listens GET requests on deposit_money page
     * @return View
     */
    @RequestMapping(value = "/home/deposit_money", method = RequestMethod.GET)
    public String depositMoney() {
        return "deposit_money";
    }

    /**
     * Method listens POST requests on deposit_money page
     * @param request
     * @param model
     * @return Redirect to another page
     */
    @RequestMapping(value = "/home/deposit_money", method = RequestMethod.POST)
    public String depositMoney(HttpServletRequest request, Model model) {
        String cardNumber = request.getParameter("cardNumber");
        String CVV = request.getParameter("CVV");
        String money = request.getParameter("money");
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (!cardNumber.matches(cardNumberRegex)) {
            model.addAttribute("wrongCardNumber", "");
            return "deposit_money";
        }

        if (!CVV.matches(cvvRegex)) {
            model.addAttribute("wrongCVV", "");
            return "deposit_money";
        }

        if (money.isEmpty() || Double.parseDouble(money) > 10000
                || (account.getCard() + Double.parseDouble(money)) > 1000000) {
            model.addAttribute("wrongAmount", "");
            return "deposit_money";
        }
        account.setCard(account.getCard() + Double.parseDouble(money));
        accountService.updateAccount(account);
        return "redirect:/home?cardNumber";
    }

    /**
     * Method listens requests on shopping_cart page
     * @param page
     * @param model
     * @param noMoney
     * @param wrongAmount
     * @return View
     */
    @RequestMapping(value = "/shopping_cart", method = RequestMethod.GET)
    public String listOrders(@RequestParam(required = false) Integer page,
                             Model model, String noMoney, String wrongAmount) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (noMoney != null) {
            model.addAttribute("noMoney", "");
        }
        if (wrongAmount != null) {
            model.addAttribute("wrongAmount", "");
        }
        List<Orders> ordersList = ordersService.getInactiveOrders(account);
        PagedListHolder<Orders> pagedListHolder = new PagedListHolder<>(ordersList);
        pagedListHolder.setPageSize(3);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
        model.addAttribute("page", page);
        if(page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("orderList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("orderList", pagedListHolder.getPageList());
        }
        model.addAttribute("totalAmount", ordersService.getTotalAmountInactiveOrders(account));
        return "shopping_cart";
    }

    /**
     * Method listens requests on block user
     * @param email
     * @return Redirect to another page
     */
    @RequestMapping(value = "/block_user")
    public String blockUser(@RequestParam(value = "accountEmail") String email) {
        accountService.blockUser(email);
        return "redirect:/all_orders";
    }

    /**
     * Method listens requests on unblock user
     * @param email
     * @return Redirect to another page
     */
    @RequestMapping(value = "/unblock_user")
    public String unblockUser(@RequestParam(value = "accountEmail") String email) {
        accountService.unblockUser(email);
        return "redirect:/all_orders";
    }

    /**
     * Method listens requests on contacts
     * @return View
     */
    @RequestMapping(value = "/contacts")
    public String contacts() {
        return "contacts";
    }

}
