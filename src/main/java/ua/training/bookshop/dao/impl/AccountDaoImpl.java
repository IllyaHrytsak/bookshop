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

@Repository
public class AccountDaoImpl implements AccountDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);

    private static final int FIRST_VALUE = 0;

    @Autowired
    private SessionFactory sessionFactory;


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

    @Override
    public void save(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(account);
        LOGGER.info("Account successfully saved. Account details: " + account);
    }

    @Override
    public void updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
        LOGGER.info("Account successfully update. Account details: " + account);
    }

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
