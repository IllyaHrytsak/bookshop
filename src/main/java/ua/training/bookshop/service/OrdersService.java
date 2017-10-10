package ua.training.bookshop.service;


import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Orders;

import java.util.List;

/**
 * OrdersService is responsible for adding, updating, removing order and other features.
 * @author Illya Hrytsak
 */
public interface OrdersService {

    /**
     * Method adds order to the DB.
     * @param bookId Id of the book
     * @param account Object of account who makes order
     * @return true if order was added successfully false otherwise
     */
    boolean addOrder(Integer bookId, Account account);

    /**
     * Method updates order in the DB.
     * @param orderId Id of the order
     * @param amount Amount of orders
     */
    void updateOrder(Integer orderId, int amount);

    /**
     * Method removes order from the DB.
     * @param orderId Id of the order
     * @param bookId Id of the book
     */
    void removeOrder(Integer orderId, Integer bookId);

    /**
     * Method returns list of all orders in the DB.
     * @return List of all orders
     */
    List<Orders> listOrders();

    /**
     * Method removes all orders from the DB.
     * @param account Object of account who wants to remove all orders.
     */
    void removeAllOrders(Account account);

    /**
     * Method returns list of all orders which aren't paid for certain account.
     * @param account Object of account
     * @return List of unpaid orders
     */
    List<Orders> getInactiveOrders(Account account);

    /**
     * Method returns a total price of unpaid orders for certain account.
     * @param account Object of account
     * @return Number of unpaid orders for account
     */
    Double getTotalAmountInactiveOrders(Account account);

    /**
     * Method returns a total price of all orders for certain account.
     * @param account Object of account
     * @return Number of all orders for account
     */
    Double getTotalAmountOrders(Account account);

    /**
     * Method makes all orders like paid for certain account.
     * @param account Object of account
     */
    void payAllOrders(Account account);

    /**
     * Method checks all orders of account, they are paid or not.
     * @param account Object of account
     * @return true if all orders are paid false otherwise
     */
    boolean isOrdersPaid(Account account);

    /**
     * Method confirms all orders for certain account.
     * @param account Object of account
     * @return true if all orders confirms false otherwise
     */
    boolean confirmOrders(Account account);

}
