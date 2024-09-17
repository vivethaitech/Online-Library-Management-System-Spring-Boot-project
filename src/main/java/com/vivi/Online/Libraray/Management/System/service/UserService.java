
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
	//private ProfileRepository profileRepo;
	@Autowired
	public UserService(UserRepository repo) {
		super();
		this.repo = repo;
		//this.profileRepo = profileRepo;
	}
	
	public List<UserEntity> getUser() {
		return repo.findAll();
	}
	

    public UserEntity getUserById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
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
	 public UserEntity findById(Long id) {
	        return repo.findById(id).orElse(null); // Returns the user or null if not found
	    }
	 
	public String updateUser(Long id, UserEntity user) {
	    UserEntity existingUser = repo.findById(id).orElse(null);
	    if (existingUser != null) {
	        existingUser.setPassword(user.getPassword());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setGender(user.getGender());
	        existingUser.setName(user.getName());
	        existingUser.setPhoneNo(user.getPhoneNo());
	        repo.save(existingUser);
	        return "User successfully updated";
	    } else {
	        throw new IllegalArgumentException("User with id number " + id + " not found.");
	    }
	}

	public void deleteUser(Long id) {
		repo.deleteById(id);
		
	}


}
