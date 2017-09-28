package ua.training.bookshop.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.dao.RoleDao;
import ua.training.bookshop.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.load(Role.class, id);
        LOGGER.info("Role successfully loaded. Role details: " + role);
        return role;
    }
}
