package com.vivi.Online.Libraray.Management.System.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	  @GetMapping("/welcome")
	    public String welcome() {
	        return "welcome";
	    }

}
