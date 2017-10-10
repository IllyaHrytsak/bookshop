package ua.training.bookshop.dao;


import ua.training.bookshop.model.Role;

/**
 * RoleDao is responsible for working with DB.
 * @author Illya Hrytsak
 */
public interface RoleDao {

    /**
     * Method returns found role by id
     * @param id Role id
     * @return Found role
     */
    Role findById(Integer id);

}
