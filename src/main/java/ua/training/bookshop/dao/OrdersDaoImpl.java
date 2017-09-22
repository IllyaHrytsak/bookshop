package ua.training.bookshop.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.model.Orders;

import java.util.List;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addOrder(Orders order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
    }

    @Override
    public void updateOrder(Orders order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
    }

    @Override
    public void removeOrder(Integer orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Orders order = session.load(Orders.class, orderId);

        if(order != null){
            session.delete(order);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> listOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Orders> orderList = session.createQuery("from Orders").list();

        if (orderList == null || orderList.isEmpty()) {
            return null;
        }

        return orderList;
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Orders orders = session.load(Orders.class, orderId);

        return orders;
    }
}
