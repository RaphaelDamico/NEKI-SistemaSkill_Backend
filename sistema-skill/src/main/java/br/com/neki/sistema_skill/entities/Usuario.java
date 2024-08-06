package br.com.neki.sistema_skill.entities;

import java.util.List;

import br.com.neki.sistema_skill.DTOs.CreateUsuarioDTO;
import br.com.neki.sistema_skill.DTOs.UsuarioDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Integer usuarioId;

	@NotBlank
	@Column(name = "login", unique = true)
	private String login;

	@NotBlank
	@Column(name = "senha")
	private String senha;

	@OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private List<UserSkill> userSkills;

	public Usuario() {
	}

	public Usuario(Integer usuarioId, @NotBlank String login, @NotBlank String senha, List<UserSkill> userSkills) {
		super();
		this.usuarioId = usuarioId;
		this.login = login;
		this.senha = senha;
		this.userSkills = userSkills;
	}

	public Usuario(UsuarioDTO usuarioDTO) {
		this.usuarioId = usuarioDTO.getUsuarioId();
		this.login = usuarioDTO.getLogin();
		this.senha = usuarioDTO.getSenha();
		this.userSkills = usuarioDTO.getUserSkills();
	}

	public Usuario(CreateUsuarioDTO createUsuarioDTO) {
		this.login = createUsuarioDTO.getLogin();
		this.senha = createUsuarioDTO.getSenha();
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
