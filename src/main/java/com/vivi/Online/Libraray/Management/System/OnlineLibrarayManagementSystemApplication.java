package com.vivi.Online.Libraray.Management.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.vivi.Online.Libraray.Management.System")
@ComponentScan(basePackages = {
	    "com.vivi.Online.Libraray.Management.System.controller",
	    "com.vivi.Online.Libraray.Management.System.swagger",
	    "com.vivi.Online.Libraray.Management.System.service",
	    "com.vivi.Online.Libraray.Management.System.security"
	})
public class OnlineLibrarayManagementSystemApplication {

public static void main(String[] args) {
		SpringApplication.run(OnlineLibrarayManagementSystemApplication.class, args);
	}

}
