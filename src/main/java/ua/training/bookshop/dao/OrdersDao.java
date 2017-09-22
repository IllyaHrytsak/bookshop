package ua.training.bookshop.dao;


import ua.training.bookshop.model.Orders;

import java.util.List;

public interface OrdersDao {

    void addOrder(Orders order);

    void updateOrder(Orders order);

    void removeOrder(Integer orderId);

    Orders getOrderById(Integer orderId);

    List<Orders> listOrders();
}
