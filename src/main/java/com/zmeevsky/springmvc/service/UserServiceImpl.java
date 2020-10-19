package com.zmeevsky.springmvc.service;

import com.zmeevsky.springmvc.dao.UserDao;
import com.zmeevsky.springmvc.entity.User;
import com.zmeevsky.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		userRepository.saveAndFlush(theUser);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userRepository.getOne(theId);
	}

	@Override
	@Transactional
	public void deleteUser(int theId) {
		User user = getUser(theId);
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public List<User> searchUsers(String theSearchName) {
		return null;
	}


//	@Autowired
//	public UserServiceImpl(UserDao userDao) {
//		this.userDao = userDao;
//	}
//
//	@Override
//	@Transactional
//	public List<User> getUsers() {
//		return new ArrayList<>(userDao.getUsers());
//	}
//
//	@Override
//	@Transactional
//	public void saveUser(User theUser) {
//		userDao.saveUser(theUser);
//	}
//
//	@Override
//	@Transactional
//	public User getUser(int theId) {
//		return userDao.getUser(theId);
//	}
//
//	@Override
//	@Transactional
//	public void deleteUser(int theId) {
//		userDao.deleteUser(theId);
//	}
//
//	@Override
//	@Transactional
//	public List<User> searchUsers(String theSearchName) {
//		return userDao.searchUsers(theSearchName);
//	}
}





