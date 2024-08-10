package br.com.neki.sistema_skill.DTOs.Skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SkillDTO {
	
	@NotNull(message = "The skill id cannot be null")
	private Integer skillId;
	
    @NotBlank(message = "The name field is required")
	private String skillName;
	
    @NotBlank(message = "The description field is required")
	private String description;
	
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$",message = "invalid url")
    @NotBlank(message = "The image field is required")
	private String image;
	
	public SkillDTO() {
	}

	public SkillDTO(Integer skillId, String skillName, String description, String image) {
		this.skillId = skillId;
		this.skillName = skillName;
		this.description = description;
		this.image = image;
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
