package ua.training.bookshop.service;


import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Orders;

import java.util.List;

public interface OrdersService {

    boolean addOrder(Integer bookId, Account account);

    void updateOrder(Integer orderId, int amount);

    void removeOrder(Integer orderId, Integer bookId);

    List<Orders> listOrders();

    void removeAllOrders(Account account);

    List<Orders> getInactiveOrders(Account account);

    Double getTotalAmountInactiveOrders(Account account);

    Double getTotalAmountOrders(Account account);

    void payAllOrders(Account account);

    boolean isOrdersPaid(Account account);

    boolean confirmOrders(Account account);

}
