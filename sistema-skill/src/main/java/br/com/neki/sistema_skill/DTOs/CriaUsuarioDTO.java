package br.com.neki.sistema_skill.DTOs;

public class CriaUsuarioDTO {
	private String login;
	private String senha;
	
	public CriaUsuarioDTO() {
	}

	public CriaUsuarioDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
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
	
}
