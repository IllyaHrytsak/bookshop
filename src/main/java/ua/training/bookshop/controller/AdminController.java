package ua.training.bookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.service.AccountService;
import ua.training.bookshop.service.OrdersService;

@Controller
public class AdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/all_orders")
    public String allOrders(Model model,
                            @RequestParam(value = "accountEmail", defaultValue = "") String accountEmail,
                            String error) {
        model.addAttribute(accountService.listAccountsWithOrders());
        if (!accountEmail.isEmpty()) {
            Account account = accountService.findByEmail(accountEmail);
            model.addAttribute("accountDetails", account);
            model.addAttribute("totalSummary", ordersService.getTotalAmountOrders(account));
            model.addAttribute("paid", ordersService.isOrdersPaid(account));
        }
        if (error != null) {
            model.addAttribute("message", "Account has unpaid orders!");
        }
        return "all_orders";
    }

}