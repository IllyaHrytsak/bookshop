package ua.training.bookshop.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.util.ReflectionTestUtils;
import ua.training.bookshop.AbstractTestConfig;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Role;

public class UserDetailsServiceTest extends AbstractTestConfig {

    @InjectMocks
    @Autowired
    private UserDetailsService userDetailsService;

    @Mock
    private AccountDao accountDao;

    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(this.
                <UserDetailsService>getTargetObject(userDetailsService), "accountDao", accountDao);
    }

    @Test
    public void testLoadByUsername() {
        String email = "123";
        String password ="123";
        String roleName = "ADMIN";
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        Role role = new Role();
        role.setRoleName(roleName);
        account.setRole(role);

        Mockito.doReturn(account).when(accountDao).findByEmail(email);

        Assert.assertEquals(email, userDetailsService.loadUserByUsername(email).getUsername());
    }


}
