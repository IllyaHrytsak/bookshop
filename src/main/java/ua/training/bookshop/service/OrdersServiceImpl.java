package ua.training.bookshop.service;


import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.dao.BookDao;
import ua.training.bookshop.dao.OrdersDao;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Book;
import ua.training.bookshop.model.Orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AccountDao accountDao;

    private static final int DEFAULT_VALUE = 1;

    @Override
    @Transactional
    public boolean addOrder(Integer bookId, Account account) {
        Book book = bookDao.getBookById(bookId);
        if (book != null) {
            for (Orders accountOrder : getInactiveOrders(account)) {
                if (isBooksEqual(accountOrder.getBook(), book)) {
                    return false;
                }
            }
            Orders orders = new Orders();
            orders.setAccount(account);
            orders.setBook(book);
            orders.setAmount(DEFAULT_VALUE);
            orders.setPrice(book.getBookPrice());
            orders.setTotalPrice(DEFAULT_VALUE * book.getBookPrice());
            orders.setOrderDate(new Date(System.currentTimeMillis()));
            orderDao.addOrder(orders);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateOrder(Integer orderId, int amount) {
        Orders orders = orderDao.getOrderById(orderId);
        orders.setAmount(amount);
        orders.setTotalPrice(orders.getPrice() * amount);
        orderDao.updateOrder(orders);
    }

    @Override
    @Transactional
    public void removeOrder(Integer orderId, Integer bookId) {
        Orders orders = orderDao.getOrderById(orderId);
        Book book = bookDao.getBookById(bookId);
        if (book != null && orders != null) {
            book.getOrders().remove(orders);
            bookDao.updateBook(book);
        }
    }

    @Override
    @Transactional
    public List<Orders> listOrders() {
        return orderDao.listOrders();
    }

    @Override
    @Transactional
    public void removeAllOrders(Account account) {
        account.getOrders().removeAll(account.getOrders());
        accountDao.updateAccount(account);
    }

    @Override
    @Transactional
    public Double getTotalAmountInactiveOrders(Account account) {
        double summary = 0;
        for (Orders orders : account.getOrders()) {
            if (!orders.isActive()) {
                summary += orders.getTotalPrice();
            }
        }
        return summary;
    }

    @Override
    @Transactional
    public Double getTotalAmountOrders(Account account) {
        double summary = 0;
        for (Orders orders : account.getOrders()) {
            summary += orders.getTotalPrice();
        }
        return summary;
    }

    @Override
    @Transactional
    public List<Orders> getInactiveOrders(Account account) {
        List<Orders> orders = new ArrayList<>();
        for (Orders order : account.getOrders()) {
            if (!order.isActive()) {
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    @Transactional
    public void payAllOrders(Account account) {
        Double summary = getTotalAmountInactiveOrders(account);
        Double balance = account.getCard() - summary;
        account.setCard(balance);
        for (Orders orders : account.getOrders()) {
            orders.setActive(true);
            orderDao.updateOrder(orders);
        }
        accountDao.updateAccount(account);
    }

    @Override
    @Transactional
    public boolean isOrdersPaid(Account account) {
        for (Orders orders : account.getOrders()) {
            if (!orders.isActive()) {
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean confirmOrders(Account account) {
        if (!isOrdersPaid(account)) {
            return false;
        }
        account.getOrders().removeAll(account.getOrders());
        accountDao.updateAccount(account);
        return true;
    }

    private boolean isBooksEqual(Book book1, Book book2) {
        return book1.getBookTitle().equals(book2.getBookTitle())
                && book1.getBookAuthor().equals(book2.getBookAuthor())
                && book1.getBookPrice().equals(book2.getBookPrice());
    }
}

