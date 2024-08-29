package com.vivi.Online.Libraray.Management.System.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customizeOpenAPI() {
		return new OpenAPI().info(new Info().title("Online Libarary Management System")
				            .description("API for managing Online libraray management system operations"));				        
	}

}
