package ua.training.bookshop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.training.bookshop.service.SecurityService;

/**
 * Base implementation of
 * {@link ua.training.bookshop.service.SecurityService}
 *
 * @author Illya Hrytsak
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    /**
     * Field for injecting realization of
     * {@link org.springframework.security.authentication.AuthenticationManager}
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Field for injecting realization of
     * {@link org.springframework.security.core.userdetails.UserDetailsService}
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.service.SecurityService}
     * @param email Email of account
     * @param password Password of account
     */
    @Override
    public void autoLogin(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
    }
}
