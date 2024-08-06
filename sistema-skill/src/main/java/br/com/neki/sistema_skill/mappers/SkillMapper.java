package br.com.neki.sistema_skill.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
import br.com.neki.sistema_skill.DTOs.CriaUsuarioDTO;
import br.com.neki.sistema_skill.DTOs.SkillDTO;
import br.com.neki.sistema_skill.DTOs.UsuarioDTO;
import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.Usuario;

@Mapper
public interface SkillMapper {
	SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);
	
	SkillDTO toSkillDTO(Skill skill);
	CriaSkillDTO toCriaSkillDTO(Skill skill);
	Skill toEntity(SkillDTO skillDTO);
	Skill toEntity(CriaSkillDTO createSkillDTO);
}
