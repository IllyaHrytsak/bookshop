package ua.training.bookshop.dao;


import ua.training.bookshop.model.Account;

import java.util.List;

/**
 * AccountDao is responsible for working with DB.
 * @author Illya Hrytsak
 */
public interface AccountDao {

    /**
     * Method finds account by email.
     * @param email Account email
     * @return Found account
     */
    Account findByEmail(String email);

    /**
     * Method saves account to the DB.
     * @param account New account
     */
    void save(Account account);

    /**
     * Method updates account in the DB.
     * @param account Updated account
     */
    void updateAccount(Account account);

    /**
     * Method returns list of all account in the DB.
     * @return List of all accounts
     */
    List<Account> listAccounts();

}
