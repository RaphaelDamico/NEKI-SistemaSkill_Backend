package br.com.neki.sistema_skill.DTOs.UserSkill;

import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.UserSkill;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UserSkillDTO {
	private Skill skill;
	
	@NotNull(message = "The value cannot be null")
	@Min(value = 1, message = "The value must be at least {value}.")
	@Max(value = 5, message = "The value must be at most {value}.")
	private Integer level;
	
	public UserSkillDTO() {
	}

	public UserSkillDTO(Skill skill, Integer level) {
		this.skill = skill;
		this.level = level;
	}
	
	public UserSkillDTO(UserSkill userSkill) {
		this.skill = userSkill.getSkill();
		this.level = userSkill.getLevel();
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
