package br.com.neki.sistema_skill.entities;

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
    
    @NotNull
    @Column(name = "nome", unique = true)
    private String nome;

	public Skill() {
	}

	public Skill(Integer skillId, @NotNull String nome) {
		this.skillId = skillId;
		this.nome = nome;
	}
	
	public Skill(CriaSkillDTO criaSkillDTO) {
		this.nome = criaSkillDTO.getNome();
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
	
}
