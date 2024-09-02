package com.vivi.Online.Libraray.Management.System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotBlank(message="User name is mandatary")
	private String name;
	@NotBlank(message="gender is mandatory")
	@Pattern(regexp="Male|Female|Other",message="gender should either Male or Female or Other")
	private String gender;
	@NotBlank(message="email is mandatory")
	@Email(message="email shoud valid")
	private String email;
	@NotBlank(message="password is mandatory")
    @Size(min = 6, message = "Password should have at least 6 characters")
	private String password;
	@NotNull(message="gender is mandatory")
	@Pattern(regexp="\\d{10}",message="phone number must be exactly 10 digit")
	private String phoneNo;

	public UserEntity(Long id, @NotBlank(message = "User name is mandatary") String name,
			@NotBlank(message = "gender is mandatory") @Pattern(regexp = "Male/Female/Other", message = "gender should either Male or Female or Other") String gender,
			@NotBlank(message = "email is mandatory") @Email(message = "email shoud valid") String email,
			@NotBlank(message = "password is mandatory") @Size(min = 6, message = "Password should have at least 6 characters") String password,
			@NotBlank(message = "gender is mandatory") @Pattern(regexp = "\\d{10}", message = "phone number must be exactly 10 digit") String phoneNo) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
	}

	public UserEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", password="
				+ password + ", phoneNo=" + phoneNo + "]";
	}

}
