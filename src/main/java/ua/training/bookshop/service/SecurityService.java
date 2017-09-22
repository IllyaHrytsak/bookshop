package ua.training.bookshop.service;




public interface SecurityService {

    String findLoggedInAccount();

    void autoLogin(String email, String password);

}
