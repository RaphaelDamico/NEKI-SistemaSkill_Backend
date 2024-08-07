package br.com.neki.sistema_skill.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AssignExistingSkillDTO {
	
	@NotNull(message = "The user id cannot be null")
	private Integer userId;
	
	@NotNull(message = "The skill id cannot be null")
	private Integer skillId;
	
	@NotNull(message = "The value cannot be null")
	@Positive(message = "The value must be greater than 0")
	private Integer level;
	
	public AssignExistingSkillDTO() {
	}

	public AssignExistingSkillDTO(Integer userId, Integer skillId, Integer level) {
		this.userId = userId;
		this.skillId = skillId;
		this.level = level;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
