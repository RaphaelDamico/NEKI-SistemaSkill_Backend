package br.com.neki.sistema_skill.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill.DTOs.CreateSkillDTO;
import br.com.neki.sistema_skill.DTOs.SkillDTO;
import br.com.neki.sistema_skill.entities.Skill;

@Mapper
public interface SkillMapper {
	SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);
	
	SkillDTO toSkillDTO(Skill skill);
	CreateSkillDTO toCreateSkillDTO(Skill skill);
	Skill toEntity(SkillDTO skillDTO);
	Skill toEntity(CreateSkillDTO createSkillDTO);
}
