package br.com.neki.sistema_skill.DTOs;

public class CriaSkillDTO {
	private String nome;

	public CriaSkillDTO() {
	}

	public CriaSkillDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
