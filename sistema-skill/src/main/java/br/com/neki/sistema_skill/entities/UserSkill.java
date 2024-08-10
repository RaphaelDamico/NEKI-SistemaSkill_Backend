package br.com.neki.sistema_skill.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.neki.sistema_skill.DTOs.UserSkill.UserSkillDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_skills_table")
public class UserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSkillId;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @Column(name = "level")
    private Integer level;

	public UserSkill() {
	}

	public UserSkill(Integer userSkillId, User user, Skill skill, Integer level) {
		this.userSkillId = userSkillId;
		this.user = user;
		this.skill = skill;
		this.level = level;
	}
	
	public UserSkill(UserSkillDTO userSkillDTO) {
		this.level = userSkillDTO.getLevel();
	}

	public Integer getUserSkillId() {
		return userSkillId;
	}

	public void setUserSkillId(Integer userSkillId) {
		this.userSkillId = userSkillId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
    
    

