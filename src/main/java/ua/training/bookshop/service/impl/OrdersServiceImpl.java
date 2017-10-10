package ua.training.bookshop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.dao.BookDao;
import ua.training.bookshop.dao.OrdersDao;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Book;
import ua.training.bookshop.model.Orders;
import ua.training.bookshop.service.OrdersService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Base implementation of
 * {@link ua.training.bookshop.service.OrdersService}
 *
 * @author Illya Hrytsak
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    /**
     * Field for injecting realization of {@link ua.training.bookshop.dao.OrdersDao}
     */
    @Autowired
    private OrdersDao orderDao;

    /**
     * Field for injecting realization of {@link ua.training.bookshop.dao.BookDao}
     */
    @Autowired
    private BookDao bookDao;

    /**
     * Field for injecting realization of {@link ua.training.bookshop.dao.AccountDao}
     */
    @Autowired
    private AccountDao accountDao;

    /**
     * Constant default value for creating total price
     */
    private static final int DEFAULT_VALUE = 1;

    /**
     * Constant for starting status of order
     */
    private static final boolean STARTING_STATUS = true;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param bookId Id of the book
     * @param account Object of account who makes order
     * @return true if order is successfully added false otherwise
     */
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

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param orderId Id of the order
     * @param amount Amount of orders
     */
    @Override
    @Transactional
    public void updateOrder(Integer orderId, int amount) {
        Orders orders = orderDao.getOrderById(orderId);
        orders.setAmount(amount);
        orders.setTotalPrice(orders.getPrice() * amount);
        orderDao.updateOrder(orders);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param orderId Id of the order
     * @param bookId Id of the book
     */
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

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @return List of all orders
     */
    @Override
    @Transactional
    public List<Orders> listOrders() {
        return orderDao.listOrders();
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account who wants to remove all orders.
     */
    @Override
    @Transactional
    public void removeAllOrders(Account account) {
        account.getOrders().removeAll(account.getOrders());
        accountDao.updateAccount(account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account
     * @return Total price of inactive orders
     */
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

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account
     * @return Total price of all orders
     */
    @Override
    @Transactional
    public Double getTotalAmountOrders(Account account) {
        double summary = 0;
        for (Orders orders : account.getOrders()) {
            summary += orders.getTotalPrice();
        }
        return summary;
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account
     * @return List of inactive orders
     */
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

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account
     */
    @Override
    @Transactional
    public void payAllOrders(Account account) {
        Double summary = getTotalAmountInactiveOrders(account);
        Double balance = account.getCard() - summary;
        account.setCard(balance);
        for (Orders orders : account.getOrders()) {
            orders.setActive(STARTING_STATUS);
            orderDao.updateOrder(orders);
        }
        accountDao.updateAccount(account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account
     * @return true if all orders are paid false otherwise
     */
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

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.OrdersService}
     * @param account Object of account
     * @return true if all orders confirmed false otherwise
     */
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

    /**
     * Method compares two input books
     * @param book1 First book
     * @param book2 Second book
     * @return true if books is equals false otherwise
     */
    private boolean isBooksEqual(Book book1, Book book2) {
        return book1.getBookTitle().equals(book2.getBookTitle())
                && book1.getBookAuthor().equals(book2.getBookAuthor())
                && book1.getBookPrice().equals(book2.getBookPrice());
    }
}

