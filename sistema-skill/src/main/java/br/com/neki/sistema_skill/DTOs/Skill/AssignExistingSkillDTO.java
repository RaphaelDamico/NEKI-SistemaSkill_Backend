package br.com.neki.sistema_skill.DTOs.Skill;

import jakarta.validation.constraints.NotNull;

public class AssignExistingSkillDTO {
	
	@NotNull(message = "The user id cannot be null")
	private Integer userId;
	
	@NotNull(message = "The skill id cannot be null")
	private Integer skillId;
	
	public AssignExistingSkillDTO() {
	}

	public AssignExistingSkillDTO(Integer userId, Integer skillId) {
		super();
		this.userId = userId;
		this.skillId = skillId;
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

}
