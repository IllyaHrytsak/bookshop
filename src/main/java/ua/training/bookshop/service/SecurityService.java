package ua.training.bookshop.service;


/**
 * SecurityService is responsible for auto-login account after sign up.
 * @author Illya Hrytsak
 */
public interface SecurityService {

    /**
     * Method authentications account by email and password.
     * @param email Email of account
     * @param password Password of account
     */
    void autoLogin(String email, String password);

}
