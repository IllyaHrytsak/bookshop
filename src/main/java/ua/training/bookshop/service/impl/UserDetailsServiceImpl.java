package ua.training.bookshop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.bookshop.dao.AccountDao;
import ua.training.bookshop.model.Account;
import ua.training.bookshop.model.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation of
 * {@link org.springframework.security.core.userdetails.UserDetailsService}
 *
 * @author Illya Hrytsak
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Field for injecting realization of
     * {@link ua.training.bookshop.dao.AccountDao}
     */
    @Autowired
    private AccountDao accountDao;

    /**
     * Method finds account by email and returns UserDetails of account
     * @param email Email account
     * @return UserDetails of found account
     * @throws UsernameNotFoundException if email of account doest find
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountDao.findByEmail(email);

        Role role = account.getRole();

        List<GrantedAuthority> grantList = new ArrayList<>();

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());

        grantList.add(authority);

        return new User(account.getEmail(), //
                account.getPassword(), grantList);
    }


}
