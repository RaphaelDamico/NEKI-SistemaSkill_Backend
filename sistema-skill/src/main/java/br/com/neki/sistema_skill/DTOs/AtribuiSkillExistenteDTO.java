package br.com.neki.sistema_skill.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AtribuiSkillExistenteDTO {
	
	@NotNull(message = "O id do usuário não pode ser nulo")
	private Integer usuarioId;
	
	@NotNull(message = "O id da skill não pode ser nulo")
	private Integer skillId;
	
	@NotNull(message = "O valor não pode ser nulo")
	@Positive(message = "O valor deve ser positivo")
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
