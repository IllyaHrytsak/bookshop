package ua.training.bookshop.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.bookshop.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Role.class, id);
    }
}
