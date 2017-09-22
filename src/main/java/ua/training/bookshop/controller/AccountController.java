package ua.training.bookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.service.AccountService;
import ua.training.bookshop.service.OrdersService;
import ua.training.bookshop.service.SecurityService;
import ua.training.bookshop.validator.RegisterValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RegisterValidator registerValidator;

    private static final String phoneNumberRegex = "^\\+380[\\d]{9}$";

    private static final String cardNumberRegex = "^[\\d]{4}(\\s?)[\\d]{4}(\\s?)[\\d]{4}(\\s?)[\\d]{4}$";

    private static final String cvvRegex = "^[0-9]{3}$";

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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new Account());

        return "registration";
    }

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

    @RequestMapping(value = "/home/new_first_name", method = RequestMethod.GET)
    public String editFirstName(Model model) {
        return "new_first_name";
    }

    @RequestMapping(value = "/home/new_first_name", method = RequestMethod.POST)
    public String editFirstName(HttpServletRequest request, Model model) {
        String newFirstName = request.getParameter("newFirstName");
        String confirmNewFirstName = request.getParameter("confirmNewFirstName");
        if (newFirstName.length() < 2) {
            model.addAttribute("error", "First name must be over 2 characters.");
            return "new_first_name";
        }

        if (!newFirstName.equals(confirmNewFirstName)) {
            model.addAttribute("error", "First name doesn't match.");
            return "new_first_name";
        }
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        account.setFirstName(newFirstName);
        accountService.updateAccount(account);
        return "redirect:/home?firstName";
    }

    @RequestMapping(value = "/home/new_last_name", method = RequestMethod.GET)
    public String editLastName(Model model) {
        return "new_last_name";
    }

    @RequestMapping(value = "/home/new_last_name", method = RequestMethod.POST)
    public String editLastName(HttpServletRequest request, Model model) {
        String newLastName = request.getParameter("newLastName");
        String confirmNewLastName = request.getParameter("confirmNewLastName");
        if (newLastName.length() < 2) {
            model.addAttribute("error", "Last name must be over 2 characters.");
            return "new_last_name";
        }

        if (!newLastName.equals(confirmNewLastName)) {
            model.addAttribute("error", "Last name doesn't match.");
            return "new_last_name";
        }
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        account.setLastName(newLastName);
        accountService.updateAccount(account);
        return "redirect:/home?lastName";
    }

    @RequestMapping(value = "/home/new_phone_number", method = RequestMethod.GET)
    public String editMobilePhone() {
        return "new_phone_number";
    }

    @RequestMapping(value = "/home/new_phone_number", method = RequestMethod.POST)
    public String editPhoneNumber(HttpServletRequest request, Model model) {
        String newPhoneNumber = request.getParameter("newPhoneNumber");
        String confirmNewPhoneNumber = request.getParameter("confirmNewPhoneNumber");

        if (!newPhoneNumber.matches(phoneNumberRegex)) {
            model.addAttribute("error", "Incorrect format phone number.");
            return "new_phone_number";
        }

        if (!newPhoneNumber.equals(confirmNewPhoneNumber)) {
            model.addAttribute("error", "Phone number doesn't match.");
            return "new_phone_number";
        }

        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        account.setPhoneNumber(newPhoneNumber);
        accountService.updateAccount(account);
        return "redirect:/home?phoneNumber";
    }

    @RequestMapping(value = "/home/deposit_money", method = RequestMethod.GET)
    public String depositMoney() {
        return "deposit_money";
    }

    @RequestMapping(value = "/home/deposit_money", method = RequestMethod.POST)
    public String depositMoney(HttpServletRequest request, Model model) {
        String cardNumber = request.getParameter("cardNumber");
        String CVV = request.getParameter("CVV");
        String money = request.getParameter("money");
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (!cardNumber.matches(cardNumberRegex)) {
            model.addAttribute("error", "Incorrect card number.");
            return "deposit_money";
        }

        if (!CVV.matches(cvvRegex)) {
            model.addAttribute("error", "Incorrect CVV.");
            return "deposit_money";
        }

        if (money.isEmpty() || Double.parseDouble(money) > 10000
                || (account.getCard() + Double.parseDouble(money)) > 1000000) {
            model.addAttribute("error", "Incorrect amount of money.");
            return "deposit_money";
        }
        account.setCard(account.getCard() + Double.parseDouble(money));
        accountService.updateAccount(account);
        return "redirect:/home?cardNumber";
    }

    @RequestMapping(value = "/shopping_cart", method = RequestMethod.GET)
    public String listOrders(Model model, String noMoney, String repeat) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (noMoney != null) {
            model.addAttribute("message", "");
        }
        model.addAttribute("orderList", ordersService.getInactiveOrders(account));
        model.addAttribute("totalAmount", ordersService.getTotalAmountInactiveOrders(account));
        return "shopping_cart";
    }

    @RequestMapping(value = "/block_user")
    public String blockUser(@RequestParam(value = "accountEmail") String email) {
        accountService.blockUser(email);
        return "redirect:/all_orders";
    }

    @RequestMapping(value = "/unblock_user")
    public String unblockUser(@RequestParam(value = "accountEmail") String email) {
        accountService.unblockUser(email);
        return "redirect:/all_orders";
    }

    @RequestMapping(value = "/contacts")
    public String contacts() {
        return "contacts";
    }

}
