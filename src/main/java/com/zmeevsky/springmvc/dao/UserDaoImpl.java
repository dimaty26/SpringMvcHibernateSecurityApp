package com.zmeevsky.springmvc.dao;

import com.zmeevsky.springmvc.entity.Role;
import com.zmeevsky.springmvc.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);
        session.saveOrUpdate(user);
    }

    @Override
    public User findByUsername(String username) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.like(root.get("email"), "%" + username + "%"));

        TypedQuery<User> query = entityManager.createQuery(cr);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

    @Override
    public List<User> searchUsers(String theSearchName) {

        Session session = entityManager.unwrap(Session.class);

        Query<User> query = null;

        if (theSearchName != null && theSearchName.trim().length() > 0) {

            //search for firstName or lastName ... case insensitive
            query = session.createQuery("from User where lower(firstName) " +
                    "like :theName or lower(lastName) like :theName", User.class);
            query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            //theSearchName is empty ... so just get all customers
            query = session.createQuery("from User", User.class);
        }

        return query.getResultList();
    }
}
