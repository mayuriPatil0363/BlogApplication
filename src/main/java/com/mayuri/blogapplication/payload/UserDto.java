package com.mayuri.blogapplication.payload;

import java.util.ArrayList;
import java.util.List;

import com.mayuri.blogapplication.model.Post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Builder
public class UserDto {
	
	private Integer id;

	@NotBlank
	private String name;
	
	@NotBlank(message = "Email cannot be blank") 
	@Email(message = "Invalid email format, missing '@' !!")  
	private String email;
	
	@NotEmpty
	@Size(min = 5, max = 8, message="Password Password size must greaterthan 4 or lessthan 9 !! ")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", 
	         message = "Password must include at least one uppercase letter, one lowercase letter, one digit, and one special character.")
	private String password;
	
	@NotBlank
	private String about;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}

	

	
	
	
}
