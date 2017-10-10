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

/**
 * Base implementation of
 * {@link ua.training.bookshop.dao.OrdersDao}
 *
 * @author Illya Hrytsak
 */
@Repository
public class OrdersDaoImpl implements OrdersDao {

    /**
     * Logger for logging class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersDaoImpl.class);

    /**
     * Field for injecting realization of {@link org.hibernate.SessionFactory}
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.OrdersDao}
     * @param order New order
     */
    @Override
    public void addOrder(Orders order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
        LOGGER.info("Order successfully added. Order details: " + order);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.OrdersDao}
     * @param order Updated book
     */
    @Override
    public void updateOrder(Orders order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
        LOGGER.info("Order successfully update. Order details: " + order);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.OrdersDao}
     * @return List of all orders
     */
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

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.OrdersDao}
     * @param orderId Order id
     * @return Found order
     */
    @Override
    public Orders getOrderById(Integer orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Orders order = session.load(Orders.class, orderId);
        LOGGER.info("Order successfully loaded. Order details: " + order);

        return order;
    }
}
