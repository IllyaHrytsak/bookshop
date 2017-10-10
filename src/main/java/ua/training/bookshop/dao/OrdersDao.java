package ua.training.bookshop.dao;


import ua.training.bookshop.model.Orders;

import java.util.List;

/**
 * OrdersDao is responsible for working with DB.
 * @author Illya Hrytsak
 */
public interface OrdersDao {

    /**
     * Method saves order to the DB.
     * @param order New order
     */
    void addOrder(Orders order);

    /**
     * Method updates order in the DB.
     * @param order Updated book
     */
    void updateOrder(Orders order);

    /**
     * Method returns found book by id
     * @param orderId Order id
     * @return Found order
     */
    Orders getOrderById(Integer orderId);

    /**
     * Method returns list of all order in the DB.
     * @return List of all orders
     */
    List<Orders> listOrders();

}
