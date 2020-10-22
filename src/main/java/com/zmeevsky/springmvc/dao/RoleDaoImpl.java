package com.zmeevsky.springmvc.dao;

import com.zmeevsky.springmvc.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getOne(int id) {
        return entityManager.find(Role.class, id);
    }
}
