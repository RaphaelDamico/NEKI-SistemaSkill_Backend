package br.com.neki.sistema_skill.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.sistema_skill.entities.UserSkill;

public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
}
