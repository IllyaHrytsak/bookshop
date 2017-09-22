package ua.training.bookshop.service;


import ua.training.bookshop.model.Account;

import java.util.List;

public interface AccountService {


    Account findByEmail(String email);

    void saveAccount(Account account);

    void updateAccount(Account account);

    List<Account> listAccountsWithOrders();

    void blockUser(String email);

    void unblockUser(String email);

}
