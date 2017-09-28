package ua.training.bookshop.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.dao.OrdersDao;
import ua.training.bookshop.model.Orders;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrder(Orders order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
        LOGGER.info("Order successfully added. Order details: " + order);
    }

    @Override
    public void updateOrder(Orders order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
        LOGGER.info("Order successfully update. Order details: " + order);
    }

    @Override
    public void removeOrder(Integer orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Orders order = session.load(Orders.class, orderId);

        if(order != null){
            session.delete(order);
        }

        LOGGER.info("Order successfully removed. Order details: " + order);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> listOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Orders> orderList = session.createQuery("from Orders").list();

        if (orderList == null || orderList.isEmpty()) {
            return null;
        }
        for (Orders orders : orderList){
            LOGGER.info("Order list: " + orders);
        }

        return orderList;
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Orders order = session.load(Orders.class, orderId);
        LOGGER.info("Order successfully loaded. Order details: " + order);

        return order;
    }
}
