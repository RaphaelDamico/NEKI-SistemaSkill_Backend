package br.com.neki.sistema_skill.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistema_skill.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	Optional<Skill> findBySkillName(String skillName);
}
