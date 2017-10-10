package ua.training.bookshop.service;


import ua.training.bookshop.model.Account;

import java.util.List;

/**
 * AccountService is responsible for creating, finding, updating and other features.
 * @author Illya Hrytsak
 */
public interface AccountService {

    /**
     * Takes email and tries to find account with this email from DB.
     * @param email Email of user
     * @return Account that has this email or null
     */
    Account findByEmail(String email);

    /**
     * Takes new Account and saves his to DB.
     * @param account New created account
     */
    void saveAccount(Account account);

    /**
     * Takes object Account, finds his in DB and updates information
     * @param account Account with updated information
     */
    void updateAccount(Account account);

    /**
     * Method returns list of accounts who have an order.
     * @return List with accounts who have an order
     */
    List<Account> listAccountsWithOrders();

    /**
     * Method blocks account with this email.
     * @param email Email account
     */
    void blockUser(String email);

    /**
     * Method unblocks account with this email.
     * @param email Email account.
     */
    void unblockUser(String email);

}
