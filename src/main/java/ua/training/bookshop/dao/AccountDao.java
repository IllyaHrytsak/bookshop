package ua.training.bookshop.dao;


import ua.training.bookshop.model.Account;

import java.util.List;

public interface AccountDao {

    Account findByEmail(String email);

    void save(Account account);

    void updateAccount(Account account);

    List<Account> listAccounts();

}
