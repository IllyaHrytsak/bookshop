package ua.training.bookshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.dao.RoleDao;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Role;
import ua.training.bookshop.service.AccountService;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of
 * {@link ua.training.bookshop.service.AccountService}
 *
 * @author Illya Hrytsak
 */
@Service
public class AccountServiceImpl implements AccountService {


    /**
     * Field for injecting realization of {@link ua.training.bookshop.dao.AccountDao}
     */
    @Autowired
    private AccountDao accountDao;

    /**
     * Field for injecting realization of {@link ua.training.bookshop.dao.RoleDao}
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * Field for injecting realization of
     * {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constant for account which role is user
     */
    private static final Integer ROLE_USER = 2;

    /**
     * Constant for account which role is blocked
     */
    private static final Integer ROLE_BLOCKED = 3;

    /**
     * Constant for account card
     */
    private static final double STARTING_MONEY = 0d;


    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.AccountService}
     * @param email Email of user
     * @return Object of found account
     */
    @Override
    @Transactional
    public Account findByEmail(String email) {
        return accountDao.findByEmail(email);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.AccountService}
     * @param account New created account
     */
    @Override
    @Transactional
    public void saveAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setCard(STARTING_MONEY);
        Role role = roleDao.findById(ROLE_USER);
        account.setRole(role);
        accountDao.save(account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.AccountService}
     * @param account Account with updated information
     */
    @Override
    @Transactional
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.AccountService}
     * @return List of accounts which have orders
     */
    @Override
    @Transactional
    public List<Account> listAccountsWithOrders() {
        List<Account> accounts = new ArrayList<>();
        for (Account account : accountDao.listAccounts()) {
            if (account.getOrders() != null && !account.getOrders().isEmpty()) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.AccountService}
     * @param email Email account
     */
    @Override
    @Transactional
    public void blockUser(String email) {
        Account account = accountDao.findByEmail(email);
        Role role = roleDao.findById(ROLE_BLOCKED);
        account.setRole(role);
        accountDao.updateAccount(account);
    }

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.AccountService}
     * @param email Email account.
     */
    @Override
    @Transactional
    public void unblockUser(String email) {
        Account account = accountDao.findByEmail(email);
        Role role = roleDao.findById(ROLE_USER);
        account.setRole(role);
        accountDao.updateAccount(account);
    }
}
