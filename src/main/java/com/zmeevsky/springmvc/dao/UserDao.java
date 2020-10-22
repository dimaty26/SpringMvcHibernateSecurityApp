package com.zmeevsky.springmvc.dao;

import com.zmeevsky.springmvc.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void saveUser(User user);

    User findByUsername(String username);

    User getUser(int id);

    void deleteUser(int id);

    List<User> searchUsers(String theSearchName);
}
