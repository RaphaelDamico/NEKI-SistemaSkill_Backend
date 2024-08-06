package br.com.neki.sistema_skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.mappers.SkillMapper;
import br.com.neki.sistema_skill.repositories.SkillRepository;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepository;

	public CriaSkillDTO save(CriaSkillDTO createSkillDTO) {
		Skill skillSave = new Skill(createSkillDTO);
		skillRepository.save(skillSave);
		return SkillMapper.INSTANCE.toCriaSkillDTO(skillSave);
	}
}
