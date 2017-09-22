package ua.training.bookshop.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.service.AccountService;
import ua.training.bookshop.service.OrdersService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrdersService ordersService;


    @RequestMapping("/add_book_to_cart")
    public String buyBook(@RequestParam(value = "code", defaultValue = "") String code) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (!ordersService.addOrder(Integer.parseInt(code), account)) {
            return "redirect:/booklist?repeat";
        }
        return "redirect:/shopping_cart";
    }

    @RequestMapping("/remove_order")
    public String removeOrder(@RequestParam(value = "code", defaultValue = "") String code,
                              @RequestParam(value = "order", defaultValue = "") String order) {
        ordersService.removeOrder(Integer.parseInt(order), Integer.parseInt(code));
        return "redirect:/shopping_cart";
    }

    @RequestMapping("/remove_all")
    public String removeAll() {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        ordersService.removeAllOrders(account);
        return "redirect:/shopping_cart";
    }

    @RequestMapping(value = "/pay_orders", method = RequestMethod.GET)
    public String payForOrders(Model model) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        Double summary = ordersService.getTotalAmountInactiveOrders(account);
        if (summary > account.getCard()) {
            return "redirect:/shopping_cart?noMoney";
        }
        model.addAttribute("orderList", ordersService.getInactiveOrders(account));
        model.addAttribute("totalAmount", summary);
        return "pay_orders";
    }

    @RequestMapping(value = "/pay_orders", method = RequestMethod.POST)
    public String payForOrders() {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        ordersService.payAllOrders(account);
        return "redirect:/success";
    }

    @RequestMapping(value = "/shopping_cart/amount")
    public String updateQuantity(HttpServletRequest request,
                                 @RequestParam(value = "orderId", defaultValue = "1") String orderId) {
        String newAmount = request.getParameter("newAmount");
        ordersService.updateOrder(Integer.parseInt(orderId), Integer.parseInt(newAmount));
        return "redirect:/shopping_cart";
    }



    @RequestMapping(value = "/success")
    public String success() {
        return "success";
    }

    @RequestMapping(value = "/confirm_orders")
    public String confirmOrders(@RequestParam(value = "accountEmail", defaultValue = "") String email) {
        Account account = accountService.findByEmail(email);
        if (!ordersService.confirmOrders(account)) {
            return "redirect:/all_orders?error";
        }
        return "redirect:/all_orders";
    }

}
