package br.com.neki.sistema_skill.DTOs;

import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.UserSkill;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UserSkillDTO {
	private Skill skill;
	
	@NotNull(message = "O valor não pode ser nulo")
	@Positive(message = "O valor deve ser positivo")
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
