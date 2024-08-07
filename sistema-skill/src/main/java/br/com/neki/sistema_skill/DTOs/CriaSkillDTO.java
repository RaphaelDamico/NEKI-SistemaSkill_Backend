package br.com.neki.sistema_skill.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CriaSkillDTO {

    @NotBlank(message = "O nome deve ser preenchido")
	private String nome;
	
    @NotBlank(message = "A descrição deve ser preenchida")
	private String descricao;
	
    @Pattern(regexp = "^(https?|ftp)://[^\s/$.?#].[^\s]*$",message = "URL inválida")
    @NotBlank(message = "A imagem deve ser preenchida")
	private String imagem;

	public CriaSkillDTO() {
	}

	public CriaSkillDTO(String nome, String descricao, String imagem) {
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
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
