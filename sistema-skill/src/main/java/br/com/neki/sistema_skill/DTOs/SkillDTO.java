package br.com.neki.sistema_skill.DTOs;

public class SkillDTO {
	private Integer SkillId;
	private String nome;
	
	public SkillDTO() {
	}

	public SkillDTO(Integer skillId, String nome) {
		super();
		SkillId = skillId;
		this.nome = nome;
	}

	public Integer getSkillId() {
		return SkillId;
	}

	public void setSkillId(Integer skillId) {
		SkillId = skillId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
