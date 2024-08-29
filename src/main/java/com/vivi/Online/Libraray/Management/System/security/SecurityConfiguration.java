package com.vivi.Online.Libraray.Management.System.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;


@EnableWebSecurity
@EnableMethodSecurity
@Component
public class SecurityConfiguration {
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encrypt) {
		
		UserDetails admin = User.withUsername("vinith").password(encrypt.encode("12345678")).roles("ADMIN").build();
		UserDetails manager = User.withUsername("kanmani").password(encrypt.encode("abcdefg")).roles("MANAGER").build();
		UserDetails incharge = User.withUsername("poppy").password(encrypt.encode("password")).roles("INCHARGE").build();
		return new InMemoryUserDetailsManager(admin,manager,incharge);
	}
	
	@Bean
	public PasswordEncoder pe() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		return  http
				       .csrf()
				       .disable().authorizeHttpRequests()
				       .requestMatchers("/book/get").permitAll()
				       .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
				       .requestMatchers("/book/**").authenticated() 
				       .and()
				       .formLogin()
				       .and()
				       .httpBasic()
				       .and()
				       .build();
				
	}
	

}
