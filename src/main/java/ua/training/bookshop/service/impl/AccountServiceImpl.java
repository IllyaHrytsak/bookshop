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

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Integer ROLE_USER = 2;

    private static final Integer ROLE_BLOCKED = 3;

    @Override
    @Transactional
    public Account findByEmail(String email) {
        return accountDao.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setCard(500d);
        Role role = roleDao.findById(ROLE_USER);
        account.setRole(role);
        accountDao.save(account);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }


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

    @Override
    @Transactional
    public void blockUser(String email) {
        Account account = accountDao.findByEmail(email);
        Role role = roleDao.findById(ROLE_BLOCKED);
        account.setRole(role);
        accountDao.updateAccount(account);
    }

    @Override
    @Transactional
    public void unblockUser(String email) {
        Account account = accountDao.findByEmail(email);
        Role role = roleDao.findById(ROLE_USER);
        account.setRole(role);
        accountDao.updateAccount(account);
    }
}
