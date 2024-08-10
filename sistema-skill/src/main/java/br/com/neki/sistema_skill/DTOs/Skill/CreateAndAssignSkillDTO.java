package br.com.neki.sistema_skill.DTOs.Skill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CreateAndAssignSkillDTO {
	
	@NotNull(message = "The user id cannot be null")
	private Integer userId;

    @NotBlank(message = "The name field is required")
	private String skillName;
	
    @NotBlank(message = "The description field is required")
	private String description;
	
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$",message = "invalid url")
    @NotBlank(message = "The image url field is required")
	private String image;
	
    @NotNull(message = "The value cannot be null")
	@Positive(message = "The value must be greater than 0")
	private Integer level;
	
	public CreateAndAssignSkillDTO() {
	}

	public CreateAndAssignSkillDTO(Integer userId, String skillName, String description, String image, Integer level) {
		this.userId = userId;
		this.skillName = skillName;
		this.description = description;
		this.image = image;
		this.level = level;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
		
}
