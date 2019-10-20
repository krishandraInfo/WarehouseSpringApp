package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.model.User;
import com.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao dao;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Transactional
	public Integer saveUser(User user) {
		user.setUserPassword(pwdEncoder.encode(user.getUserPassword()));
		return dao.saveUser(user);
	}

	@Transactional
	public void updateUser(User user) {

		dao.updateUser(user);
	}

	@Transactional
	public void deleteUser(Integer userId) {
		
		dao.deleteUser(userId);
	}

	@Transactional(readOnly=true)
	public User getUserById(Integer userId) {

		return dao.getUserById(userId);
	}

	@Transactional(readOnly=true)
	public List<User> getAllUsers() {

		return dao.getAllUsers();
	}

	@Transactional(readOnly=true)
	public List<Object[]> getUsersCount() {
		
		return dao.getUsersCount();
	}

	@Transactional(readOnly=true)
	public boolean isEmailOrMobileExist(String type, String userEmailOrmobile) {
		return dao.isEmailOrMobileExist(type, userEmailOrmobile);
	}
}
