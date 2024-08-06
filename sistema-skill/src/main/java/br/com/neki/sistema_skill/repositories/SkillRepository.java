package br.com.neki.sistema_skill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistema_skill.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
