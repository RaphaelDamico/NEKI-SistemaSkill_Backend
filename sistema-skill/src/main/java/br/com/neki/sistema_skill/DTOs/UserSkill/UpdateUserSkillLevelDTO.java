package br.com.neki.sistema_skill.DTOs.UserSkill;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateUserSkillLevelDTO {
	
	@NotNull
	Integer userSkillId;
	
	@NotNull
	@Min(value = 1, message = "The value must be at least {value}.")
	@Max(value = 5, message = "The value must be at most {value}.")
	Integer level;

	public UpdateUserSkillLevelDTO() {
	}

	public UpdateUserSkillLevelDTO(@NotNull Integer userSkillId, @NotNull @Positive Integer level) {
		this.userSkillId = userSkillId;
		this.level = level;
	}

	public Integer getUserSkillId() {
		return userSkillId;
	}

	public void setUserSkillId(Integer userSkillId) {
		this.userSkillId = userSkillId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
