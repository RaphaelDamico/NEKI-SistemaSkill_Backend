package br.com.neki.sistema_skill.DTOs.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateUserDTO {
	
    @NotBlank(message = "The username field is required")
	private String username;
	
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
    	    message = "The password must contain at least one uppercase letter, one number, one special character, and be between 8 and 30 characters long")
    @NotBlank(message = "The password field is required")
	private String password;
	
	public CreateUserDTO() {
	}

	public CreateUserDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
