package br.com.neki.sistema_skill.DTOs;

import java.util.List;

import br.com.neki.sistema_skill.entities.UserSkill;

public class UsuarioDTO {
	private Integer usuarioId;
	private String login;
	private String senha;
	private List<UserSkill> userSkills;
	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Integer usuarioId, String login, String senha, List<UserSkill> userSkills) {
		super();
		this.usuarioId = usuarioId;
		this.login = login;
		this.senha = senha;
		this.userSkills = userSkills;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	
	
}
