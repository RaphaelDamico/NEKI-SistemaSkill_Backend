package br.com.neki.sistema_skill.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CriaUsuarioDTO {
	
    @NotBlank(message = "O login deve ser preenchido")
	private String login;
	
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
    	    message = "A senha deve ter pelo menos uma letra maiúscula, um número, um caractere especial, e entre 8 e 30 caracteres")
    @NotBlank(message = "A senha deve ser preenchida")
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
