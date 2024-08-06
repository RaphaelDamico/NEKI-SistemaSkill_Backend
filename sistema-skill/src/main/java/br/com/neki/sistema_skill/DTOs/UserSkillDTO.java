package br.com.neki.sistema_skill.DTOs;

import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.UserSkill;

public class UserSkillDTO {
	private Skill skill;
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
