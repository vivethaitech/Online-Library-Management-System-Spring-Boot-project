package com.vivi.Online.Libraray.Management.System.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivi.Online.Libraray.Management.System.Entity.UserEntity;
import com.vivi.Online.Libraray.Management.System.ExceptionHandling.EmailAlreadyExist;
import com.vivi.Online.Libraray.Management.System.repository.UserRepository;
@Service
public class UserService {
	
	private UserRepository repo;
	@Autowired
	public UserService(UserRepository repo) {
		this.repo=repo;
	}
	
	public List<UserEntity> getUser() {
		return repo.findAll();
	}

	public String addUser(UserEntity user) {
		List<UserEntity> list = repo.findAll();
		for(UserEntity users:list){
			if(users.getEmail().equals(user.getEmail())) {
				throw new EmailAlreadyExist("Email Already Exist");
			}
		}
		repo.save(user);
		return"Book added successfully";
	}
	
	

}
