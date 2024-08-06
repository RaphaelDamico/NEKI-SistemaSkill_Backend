package br.com.neki.sistema_skill.DTOs;

public class CriaEAtribuiSkillDTO {
	private Integer usuarioId;
	
	private String nome;
	private Integer level;
	
	public CriaEAtribuiSkillDTO() {
	}

	public CriaEAtribuiSkillDTO(Integer usuarioId, String nome, Integer level) {
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.level = level;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
		
}
