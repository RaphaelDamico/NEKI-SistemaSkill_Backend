package br.com.neki.sistema_skill.DTOs;

import java.util.List;

import br.com.neki.sistema_skill.entities.UserSkill;
import jakarta.validation.constraints.NotBlank;

public class UserDetailsDTO {
	private Integer userId;
	
    @NotBlank(message = "The username is required")
	private String username;
	
	private List<UserSkill> userSkills;
	
	public UserDetailsDTO() {
	}

	public UserDetailsDTO(Integer userId, String username, List<UserSkill> userSkills) {
		this.userId = userId;
		this.username = username;
		this.userSkills = userSkills;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

}
