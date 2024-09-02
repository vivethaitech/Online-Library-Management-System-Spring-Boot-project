package com.vivi.Online.Libraray.Management.System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		List<UserEntity> user=service.getUser();
		model.addAttribute("user", user);
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
}
