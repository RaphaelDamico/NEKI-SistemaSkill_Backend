package br.com.neki.sistema_skill.DTOs;

public class AtribuiSkillExistenteDTO {
	private Integer usuarioId;
	private Integer skillId;
	private Integer level;
	
	public AtribuiSkillExistenteDTO() {
	}

	public AtribuiSkillExistenteDTO(Integer usuarioId, Integer skillId, Integer level) {
		this.usuarioId = usuarioId;
		this.skillId = skillId;
		this.level = level;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
