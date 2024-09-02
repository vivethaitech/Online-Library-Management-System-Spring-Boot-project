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
	
	public UserEntity findByPhoneNo(String phoneNo) {
		return repo.findByPhoneNo(phoneNo);
	}

	public String updateBook(String phoneNo, UserEntity user) {
		UserEntity existingUser = repo.findByPhoneNo(phoneNo);
		if(existingUser!=null) {
			existingUser.setPassword(user.getPassword());
			existingUser.setEmail(user.getEmail());
			existingUser.setGender(user.getGender());
			existingUser.setName(user.getName());
			existingUser.setPhoneNo(user.getPhoneNo());
			repo.save(existingUser);
			return "User successfully Updated";
		}
		return phoneNo;
		
	}

}
