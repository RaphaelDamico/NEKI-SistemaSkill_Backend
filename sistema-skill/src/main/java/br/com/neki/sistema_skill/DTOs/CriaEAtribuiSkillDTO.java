package br.com.neki.sistema_skill.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class CriaEAtribuiSkillDTO {
	
	@NotNull(message = "O id do usuário não pode ser nulo")
	private Integer usuarioId;

    @NotBlank(message = "O nome deve ser preenchido")
	private String nome;
	
    @NotBlank(message = "A descrição deve ser preenchida")
	private String descricao;
	
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$",message = "URL inválida")
    @NotBlank(message = "O url da imagem deve ser preenchido")
	private String imagem;
	
    @NotNull(message = "O valor não pode ser nulo")
	@Positive(message = "O valor deve ser maior 0")
	private Integer level;
	
	public CriaEAtribuiSkillDTO() {
	}

	public CriaEAtribuiSkillDTO(Integer usuarioId, String nome, String descricao, String imagem, Integer level) {
		this.usuarioId = usuarioId;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
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
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
		
}
