package br.com.neki.sistema_skill.entities;

import br.com.neki.sistema_skill.DTOs.Skill.AssignExistingSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.CreateAndAssignSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.CreateSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.SkillDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills_table")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;
    
    @Column(name = "skill_name", unique = true)
    private String skillName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "image")
    private String image;

	public Skill() {
	}

	public Skill(Integer skillId, String skillName, String description, String image) {
		this.skillId = skillId;
		this.skillName = skillName;
		this.description = description;
		this.image = image;
	}

	public Skill(CreateSkillDTO createSkillDTO) {
		this.skillName = createSkillDTO.getSkillName();
		this.description = createSkillDTO.getDescription();
		this.image = createSkillDTO.getImage();
	}
	
	public Skill(CreateAndAssignSkillDTO createAndAssignSkillDTO) {
		this.skillName = createAndAssignSkillDTO.getSkillName();
		this.description = createAndAssignSkillDTO.getDescription();
		this.image = createAndAssignSkillDTO.getImage();
	}
	
	public Skill(AssignExistingSkillDTO assignExistingSkillDTO) {
		this.skillId = assignExistingSkillDTO.getSkillId();
	}
	
	public Skill(SkillDTO skillDTO) {
		this.skillId = skillDTO.getSkillId();
		this.skillName = skillDTO.getSkillName();
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
