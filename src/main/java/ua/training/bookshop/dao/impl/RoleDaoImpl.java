package ua.training.bookshop.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.dao.RoleDao;
import ua.training.bookshop.model.Role;

/**
 * Base implementation of
 * {@link ua.training.bookshop.dao.RoleDao}
 *
 * @author Illya Hrytsak
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    /**
     * Logger for logging class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

    /**
     * Field for injecting realization of {@link org.hibernate.SessionFactory}
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Implementation method from
     * {@link ua.training.bookshop.dao.RoleDao}
     * @param id Role id
     * @return Found role
     */
    @Override
    public Role findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.load(Role.class, id);
        LOGGER.info("Role successfully loaded. Role details: " + role);
        return role;
    }
}
