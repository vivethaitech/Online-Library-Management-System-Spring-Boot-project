package com.vivi.Online.Libraray.Management.System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vivi.Online.Libraray.Management.System.Entity.UserEntity;
import com.vivi.Online.Libraray.Management.System.ExceptionHandling.EmailAlreadyExist;
import com.vivi.Online.Libraray.Management.System.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService service;
	@Autowired
	public UserController(UserService service) {
		this.service=service;
	}
	//get user--------------------
	@GetMapping("/get")
	public String getBooks(Model model) {
		List<UserEntity> users=service.getUser();
		model.addAttribute("users", users);
		return "user/userList";
	}
	
	//add user-------------------
	
	@GetMapping("/post")
	@PreAuthorize("hasRole('ROLE_INCHARGE')")
	public String UserForm(Model model) {
		UserEntity user = new UserEntity();
		model.addAttribute("user",user);
		return "user/userForm";
	}
    @PostMapping("/post")
	public String addUser(@ModelAttribute @Valid UserEntity user,Model model,BindingResult bind) {
    	if(bind.hasErrors()) {
    		model.addAttribute("error", bind.getAllErrors());
    		return "user/UserError";
    	}
    	
    	try {
		service.addUser(user);
		model.addAttribute("user", user);
		return "user/userAdded";
    	} catch(EmailAlreadyExist emailExist) {
    		return "library/error";
    	}
	}
    
    //update user
    @GetMapping("/update/{phoneNo}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUserForm(@PathVariable String phoneNo, Model model) {
    	UserEntity user = service.findByPhoneNo(phoneNo);
    	if(user==null) {
    		return "library/error";		
    	}
    	model.addAttribute("users", user);
    	return "user/updateForm";
    }
    
    @PostMapping("/update/{phoneNo}")
    public String updateUser(@PathVariable String phoneNo, @ModelAttribute UserEntity user ) {
    	try {
    		service.updateBook(phoneNo,user);
    		return "redirect:/user/get";
    	}
    	catch (Exception e){
    		return "library/error";
    	}
    }
}
