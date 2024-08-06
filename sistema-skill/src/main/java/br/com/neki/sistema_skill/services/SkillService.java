package br.com.neki.sistema_skill.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill.DTOs.AtribuiSkillExistenteDTO;
import br.com.neki.sistema_skill.DTOs.CriaEAtribuiSkillDTO;
import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
import br.com.neki.sistema_skill.DTOs.SkillDTO;
import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.UserSkill;
import br.com.neki.sistema_skill.entities.Usuario;
import br.com.neki.sistema_skill.exceptions.EntityNotFoundException;
import br.com.neki.sistema_skill.exceptions.SkillAlreadyExistsException;
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
		criaSkillDTO.setNome(capitalizeFirstLetter(criaSkillDTO.getNome()));
		if (skillRepository.findByNome(criaSkillDTO.getNome()).isPresent())
			throw new SkillAlreadyExistsException("Skill já existe com o nome: " + criaSkillDTO.getNome());
		Skill skillSave = new Skill(criaSkillDTO);
		skillRepository.save(skillSave);
		return SkillMapper.INSTANCE.toCriaSkillDTO(skillSave);
	}

	public CriaSkillDTO saveAndAddToUser(CriaEAtribuiSkillDTO criaEAtribuiSkillDTO) {
		criaEAtribuiSkillDTO.setNome(capitalizeFirstLetter(criaEAtribuiSkillDTO.getNome()));
		if (skillRepository.findByNome(criaEAtribuiSkillDTO.getNome()).isPresent())
			throw new SkillAlreadyExistsException("Skill já existe com o nome: " + criaEAtribuiSkillDTO.getNome());
		Skill skillSave = new Skill(criaEAtribuiSkillDTO);
		skillRepository.save(skillSave);
		Usuario usuario = usuarioRepository.findById(criaEAtribuiSkillDTO.getUsuarioId())
				.orElseThrow(() -> new EntityNotFoundException(
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

	public List<SkillDTO> findAll() {
		List<Skill> skills = skillRepository.findAll();
		if (skills.isEmpty())
			throw new NoSuchElementException("Nenhuma skill encontrada!");
		List<SkillDTO> skillsDTO = new ArrayList<>();
		for (Skill skill : skills) {
			skillsDTO.add(SkillMapper.INSTANCE.toSkillDTO(skill));
		}
		return skillsDTO;
	}

	public UserSkill addExistingSkillToUser(AtribuiSkillExistenteDTO atribuiSkillExistenteDTO) {
		Skill skill = skillRepository.findById(atribuiSkillExistenteDTO.getSkillId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Nenhuma skill encontrada com o id: " + atribuiSkillExistenteDTO.getSkillId()));
		Usuario usuario = usuarioRepository.findById(atribuiSkillExistenteDTO.getUsuarioId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Nenhum usuário encontrado com o id: " + atribuiSkillExistenteDTO.getUsuarioId()));
		UserSkill userSkill = new UserSkill();
		userSkill.setSkill(skill);
		userSkill.setUsuario(usuario);
		userSkill.setLevel(atribuiSkillExistenteDTO.getLevel());
		usuario.getUserSkills().add(userSkill);
		userSkillRepository.save(userSkill);
		usuarioRepository.save(usuario);

		return userSkill;
	}

	private String capitalizeFirstLetter(String nome) {
		if (nome == null || nome.isEmpty()) {
			return nome;
		}
		return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
	}

}
