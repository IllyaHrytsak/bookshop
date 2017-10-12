package ua.training.bookshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
import java.util.List;

/**
 * Controller for order requests
 */
@Controller
public class OrderController {

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.AccountService}
     */
    @Autowired
    private AccountService accountService;

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.service.OrdersService}
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * Constant for start page
     */
    private static final int START_PAGE = 0;

    /**
     * Constant for number of max orders on the books page
     */
    private static final int ORDERS_PAGE_SIZE = 5;

    /**
     * Method listens requests for add_book_to_cart page
     * @param code
     * @return Redirect to another view
     */
    @RequestMapping("/add_book_to_cart")
    public String buyBook(@RequestParam(value = "code", defaultValue = "") String code) {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        if (!ordersService.addOrder(Integer.parseInt(code), account)) {
            return "redirect:/booklist?repeat";
        }
        return "redirect:/shopping_cart";
    }

    /**
     * Method listens requests for remove_order page
     * @param code
     * @param order
     * @return Redirect to another view
     */
    @RequestMapping("/remove_order")
    public String removeOrder(@RequestParam(value = "code", defaultValue = "") String code,
                              @RequestParam(value = "order", defaultValue = "") String order) {
        ordersService.removeOrder(Integer.parseInt(order), Integer.parseInt(code));
        return "redirect:/shopping_cart";
    }

    /**
     * Method listens requests for remove_all page
     * @return Redirect to another view
     */
    @RequestMapping("/remove_all")
    public String removeAll() {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        ordersService.removeAllOrders(account);
        return "redirect:/shopping_cart";
    }

    /**
     * Method listens GET requests for pay_orders page
     * @param model
     * @return View
     */
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

    /**
     * Method listens POST requests for pay_orders_page
     * @return Redirect to another view
     */
    @RequestMapping(value = "/pay_orders", method = RequestMethod.POST)
    public String payForOrders() {
        String accountEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findByEmail(accountEmail);
        ordersService.payAllOrders(account);
        return "redirect:/success";
    }

    /**
     * Method listens requests for amount page
     * @param request
     * @param orderId
     * @return Redirect to another view
     */
    @RequestMapping(value = "/shopping_cart/amount")
    public String updateQuantity(HttpServletRequest request,
                                 @RequestParam(value = "orderId", defaultValue = "") String orderId) {
        String newAmount = request.getParameter("newAmount");
        if (Integer.parseInt(newAmount) < 1|| Integer.parseInt(newAmount) > 10) {
            return "redirect:/shopping_cart?wrongAmount";
        }
        if (!orderId.isEmpty()) {
            ordersService.updateOrder(Integer.parseInt(orderId), Integer.parseInt(newAmount));
        }
        return "redirect:/shopping_cart";
    }

    /**
     * Method listens requests for success page
     * @return View
     */
    @RequestMapping(value = "/success")
    public String success() {
        return "success";
    }

    /**
     * Method listens requests for all_orders page
     * @param model
     * @param accountEmail
     * @param error
     * @return View
     */
    @RequestMapping(value = "/all_orders")
    public String allOrders(Model model,
                            @RequestParam(value = "accountEmail", defaultValue = "") String accountEmail,
                            @RequestParam(required = false) Integer page,
                            String error) {
        if (!accountEmail.isEmpty()) {
            Account account = accountService.findByEmail(accountEmail);
            model.addAttribute("accountDetails", account);
            model.addAttribute("totalSummary", ordersService.getTotalAmountOrders(account));
            model.addAttribute("paid", ordersService.isOrdersPaid(account));
        }
        if (error != null) {
            model.addAttribute("message", "");
        }

        List<Account> accountList = accountService.listAccountsWithOrders();
        PagedListHolder<Account> pagedListHolder = new PagedListHolder<>(accountList);
        pagedListHolder.setPageSize(ORDERS_PAGE_SIZE);
        model.addAttribute("maxPages", pagedListHolder.getPageCount());
        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
        model.addAttribute("page", page);
        if(page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(START_PAGE);
            model.addAttribute("accountList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("accountList", pagedListHolder.getPageList());
        }
        return "all_orders";
    }

    /**
     * Method listens requests for confirm_orders page
     * @param email
     * @return Redirect to another page
     */
    @RequestMapping(value = "/confirm_orders")
    public String confirmOrders(@RequestParam(value = "accountEmail", defaultValue = "") String email) {
        Account account = accountService.findByEmail(email);
        if (!ordersService.confirmOrders(account)) {
            return "redirect:/all_orders?error";
        }
        return "redirect:/all_orders";
    }

}
