package com.mindtree.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.assignment.entity.CartEntity;
import com.mindtree.assignment.entity.UserEntity;
import com.mindtree.assignment.model.User;
import com.mindtree.assignment.repository.CartRepository;
import com.mindtree.assignment.repository.UserRepository;
import com.mindtree.assignment.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Override
	public List<User> findAllUser() {
		List<UserEntity> userEntityList = userRepo.findAll();
		List<User> userList = new ArrayList<User>();
		userEntityList.forEach(entity -> {
			User user = new User();
			BeanUtils.copyProperties(entity, user);
			userList.add(user);
		});
		return userList;
	}

	@Override
	public User addUser(User user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity = userRepo.save(userEntity);
		BeanUtils.copyProperties(userEntity, user);
		
		CartEntity cartEntity = new CartEntity();
		cartEntity.setUserid(userEntity.getUserid());
		cartRepo.save(cartEntity);
		
		return user;
	}

}
