package br.com.neki.sistema_skill.entities;

import br.com.neki.sistema_skill.DTOs.AtribuiSkillExistenteDTO;
import br.com.neki.sistema_skill.DTOs.CriaEAtribuiSkillDTO;
import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
import br.com.neki.sistema_skill.DTOs.SkillDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;
    
    @Column(name = "nome", unique = true)
    private String nome;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "imagem")
    private String imagem;

	public Skill() {
	}

	public Skill(Integer skillId, @NotNull String nome, String descricao, String imagem) {
		this.skillId = skillId;
		this.nome = nome;
		this.descricao = descricao;
		this.imagem = imagem;
	}

	public Skill(CriaSkillDTO criaSkillDTO) {
		this.nome = criaSkillDTO.getNome();
		this.descricao = criaSkillDTO.getDescricao();
		this.imagem = criaSkillDTO.getImagem();
	}
	
	public Skill(CriaEAtribuiSkillDTO criaEAtribuiSkillDTO) {
		this.nome = criaEAtribuiSkillDTO.getNome();
		this.descricao = criaEAtribuiSkillDTO.getDescricao();
		this.imagem = criaEAtribuiSkillDTO.getImagem();
	}
	
	public Skill(AtribuiSkillExistenteDTO atribuiSkillExistenteDTO) {
		this.skillId = atribuiSkillExistenteDTO.getSkillId();
	}
	
	public Skill(SkillDTO skillDTO) {
		this.skillId = skillDTO.getSkillId();
		this.nome = skillDTO.getNome();
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
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
