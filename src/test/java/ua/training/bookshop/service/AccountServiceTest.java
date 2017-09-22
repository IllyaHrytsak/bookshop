package ua.training.bookshop.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Orders;

import ua.training.bookshop.AbstractTestConfig;

import java.util.*;


public class AccountServiceTest extends AbstractTestConfig {


    @Mock
    private AccountDao accountDao;

    @InjectMocks
    @Autowired
    private AccountService accountService;


    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(this.
                <AccountService>getTargetObject(accountService), "accountDao", accountDao);
    }


    @Test
    public void testFindByEmail() {
        String email = "ivan@gmail.com";
        Account account = new Account();
        account.setEmail(email);
        Mockito.doReturn(account).when(accountDao).findByEmail(email);

        Account result = accountService.findByEmail(email);

        Assert.assertEquals(account, result);

    }

    @Test
    public void testListAccountsWithOrders() {
        Account account = new Account();
        Orders order = new Orders();
        Set<Orders> orders = new HashSet<>();
        orders.add(order);
        account.setOrders(orders);
        Mockito.when(accountDao.listAccounts()).thenReturn(Collections.singletonList(account));

        Assert.assertFalse(accountService.listAccountsWithOrders().isEmpty());

        Mockito.when(accountDao.listAccounts()).thenReturn(new ArrayList<>());

        Assert.assertTrue(accountService.listAccountsWithOrders().isEmpty());
    }


}
