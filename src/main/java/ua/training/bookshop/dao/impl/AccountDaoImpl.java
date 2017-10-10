package ua.training.bookshop.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.model.Account;

import java.util.List;

/**
 * Base implementation of
 * {@link ua.training.bookshop.dao.AccountDao}
 *
 * @author Illya Hrytsak
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    /**
     * Logger for logging class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);

    /**
     * Constant for taking first element from collection
     */
    private static final int FIRST_VALUE = 0;

    /**
     * Field for injecting realization of {@link org.hibernate.SessionFactory}
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.AccountDao}
     * @param email Account email
     * @return Found account
     */
    @Override
    @SuppressWarnings("unchecked")
    public Account findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account where email = :email");
        query.setParameter("email", email);
        List list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        Account account = (Account) list.get(FIRST_VALUE);
        LOGGER.info("Account successfully loaded. Account details: " + account);
        return account;
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.AccountDao}
     * @param account New account
     */
    @Override
    public void save(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(account);
        LOGGER.info("Account successfully saved. Account details: " + account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.AccountDao}
     * @param account Updated account
     */
    @Override
    public void updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
        LOGGER.info("Account successfully update. Account details: " + account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.AccountDao}
     * @return List of all accounts
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Account> listAccounts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Account> accountList = session.createQuery("from Account").list();

        if (accountList == null || accountList.isEmpty()) {
            return null;
        }
        for (Account account : accountList){
            LOGGER.info("Account list: " + account);
        }

        return accountList;
    }

}
