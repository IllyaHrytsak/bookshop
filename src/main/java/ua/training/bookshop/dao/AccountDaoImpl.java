package ua.training.bookshop.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.model.Account;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {


    @Autowired
    private SessionFactory sessionFactory;

    private static final int FIRST_VALUE = 0;

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
        return (Account) list.get(FIRST_VALUE);
    }

    @Override
    public void save(Account account) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Account> listAccounts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Account> accountList = session.createQuery("from Account").list();

        if (accountList == null || accountList.isEmpty()) {
            return null;
        }

        return accountList;
    }
}
