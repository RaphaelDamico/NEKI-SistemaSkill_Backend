package br.com.neki.sistema_skill.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill.DTOs.UserSkillDTO;
import br.com.neki.sistema_skill.entities.UserSkill;

@Mapper
public interface UserSkillMapper {
	UserSkillMapper INSTANCE = Mappers.getMapper(UserSkillMapper.class);
	
	UserSkillDTO toUserSkillDTO(UserSkill userSkill);
	UserSkill toUserSkillEntity(UserSkillDTO UserSkillDTO);
}
