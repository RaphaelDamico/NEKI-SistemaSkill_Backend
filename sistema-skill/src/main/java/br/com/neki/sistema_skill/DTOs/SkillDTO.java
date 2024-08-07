package br.com.neki.sistema_skill.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SkillDTO {
	
	@NotNull(message = "O id da skill não pode ser nulo")
	private Integer SkillId;
	
    @NotBlank(message = "O nome deve ser preenchido")
	private String nome;
	
    @NotBlank(message = "A descrição deve ser preenchida")
	private String descricao;
	
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$",message = "URL inválida")
    @NotBlank(message = "A imagem deve ser preenchida")
	private String imagem;
	
	public SkillDTO() {
	}

	public SkillDTO(Integer skillId, String nome, String descricao, String imagem) {
		SkillId = skillId;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
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
	
}
