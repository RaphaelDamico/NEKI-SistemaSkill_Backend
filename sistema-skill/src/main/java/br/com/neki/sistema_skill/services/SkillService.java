package br.com.neki.sistema_skill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill.DTOs.CriaEAtribuiSkillDTO;
import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.UserSkill;
import br.com.neki.sistema_skill.entities.Usuario;
import br.com.neki.sistema_skill.exceptions.EntityNotFoundException;
import br.com.neki.sistema_skill.mappers.SkillMapper;
import br.com.neki.sistema_skill.repositories.SkillRepository;
import br.com.neki.sistema_skill.repositories.UserSkillRepository;
import br.com.neki.sistema_skill.repositories.UsuarioRepository;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UserSkillRepository userSkillRepository;

	public CriaSkillDTO save(CriaSkillDTO criaSkillDTO) {
		Skill skillSave = new Skill(criaSkillDTO);
		skillRepository.save(skillSave);
		return SkillMapper.INSTANCE.toCriaSkillDTO(skillSave);
	}
	
	public CriaSkillDTO saveAndAddToUser(CriaEAtribuiSkillDTO criaEAtribuiSkillDTO) {
		Skill skillSave = new Skill(criaEAtribuiSkillDTO);
		skillRepository.save(skillSave);
		Usuario usuario = usuarioRepository.findById(criaEAtribuiSkillDTO.getUsuarioId()).orElseThrow(
				() -> new EntityNotFoundException(
						"Nenhum usuário encontrado com o id: " + criaEAtribuiSkillDTO.getUsuarioId()));
		UserSkill userSkill = new UserSkill();
		userSkill.setSkill(skillSave);
	    userSkill.setUsuario(usuario);
	    userSkill.setLevel(criaEAtribuiSkillDTO.getLevel());
	    usuario.getUserSkills().add(userSkill);
	    userSkillRepository.save(userSkill);
	    usuarioRepository.save(usuario);
		
		return SkillMapper.INSTANCE.toCriaSkillDTO(skillSave);
	}
}
