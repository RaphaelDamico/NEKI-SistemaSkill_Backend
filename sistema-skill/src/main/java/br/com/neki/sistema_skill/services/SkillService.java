package br.com.neki.sistema_skill.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill.DTOs.Skill.AssignExistingSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.CreateAndAssignSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.CreateSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.SkillDTO;
import br.com.neki.sistema_skill.DTOs.UserSkill.UpdateUserSkillLevelDTO;
import br.com.neki.sistema_skill.DTOs.UserSkill.UserSkillDTO;
import br.com.neki.sistema_skill.entities.Skill;
import br.com.neki.sistema_skill.entities.User;
import br.com.neki.sistema_skill.entities.UserSkill;
import br.com.neki.sistema_skill.exceptions.EntityNotFoundException;
import br.com.neki.sistema_skill.exceptions.SkillAlreadyExistsException;
import br.com.neki.sistema_skill.mappers.SkillMapper;
import br.com.neki.sistema_skill.mappers.UserSkillMapper;
import br.com.neki.sistema_skill.repositories.SkillRepository;
import br.com.neki.sistema_skill.repositories.UserRepository;
import br.com.neki.sistema_skill.repositories.UserSkillRepository;

@Service
public class SkillService {

	@Autowired
	SkillRepository skillRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserSkillRepository userSkillRepository;

	public CreateSkillDTO save(CreateSkillDTO createSkillDTO) {
		createSkillDTO.setSkillName(createSkillDTO.getSkillName().toUpperCase());
		if (skillRepository.findBySkillName(createSkillDTO.getSkillName()).isPresent())
			throw new SkillAlreadyExistsException(
					"A skill with this name already exists: " + createSkillDTO.getSkillName());
		Skill skillSave = new Skill(createSkillDTO);
		skillRepository.save(skillSave);
		return SkillMapper.INSTANCE.toCreateSkillDTO(skillSave);
	}

	public CreateSkillDTO saveAndAddToUser(CreateAndAssignSkillDTO createAndAssignSkillDTO) {
		createAndAssignSkillDTO.setSkillName(createAndAssignSkillDTO.getSkillName().toUpperCase());
		if (skillRepository.findBySkillName(createAndAssignSkillDTO.getSkillName()).isPresent())
			throw new SkillAlreadyExistsException(
					"A skill with this name already exists: " + createAndAssignSkillDTO.getSkillName());
		Skill skillSave = new Skill(createAndAssignSkillDTO);
		skillRepository.save(skillSave);
		User user = userRepository.findById(createAndAssignSkillDTO.getUserId()).orElseThrow(
				() -> new EntityNotFoundException("No user found with id: " + createAndAssignSkillDTO.getUserId()));
		UserSkill userSkill = new UserSkill();
		userSkill.setSkill(skillSave);
		userSkill.setUser(user);
		userSkill.setLevel(createAndAssignSkillDTO.getLevel());
		user.getUserSkills().add(userSkill);
		userSkillRepository.save(userSkill);
		userRepository.save(user);
		return SkillMapper.INSTANCE.toCreateSkillDTO(skillSave);
	}

	public List<SkillDTO> findAll() {
		List<Skill> skills = skillRepository.findAll();
		if (skills.isEmpty())
			throw new NoSuchElementException("No skill found!");
		List<SkillDTO> skillsDTO = new ArrayList<>();
		for (Skill skill : skills) {
			skillsDTO.add(SkillMapper.INSTANCE.toSkillDTO(skill));
		}
		return skillsDTO;
	}

	public List<UserSkill> addExistingSkillToUser(List<AssignExistingSkillDTO> listAssignExistingSkillDTO) {
		User user = userRepository.findById(listAssignExistingSkillDTO.get(0).getUserId())
				.orElseThrow(() -> new EntityNotFoundException(
						"No user found with id: " + listAssignExistingSkillDTO.get(0).getUserId()));
		for (AssignExistingSkillDTO assignExistingSkillDTO : listAssignExistingSkillDTO) {
			Skill skill = skillRepository.findById(assignExistingSkillDTO.getSkillId())
					.orElseThrow(() -> new EntityNotFoundException(
							"No skill found with id: " + assignExistingSkillDTO.getSkillId()));
			boolean alreadyExists = user.getUserSkills().stream()
					.anyMatch(userSkill -> userSkill.getSkill().getSkillId().equals(skill.getSkillId()));
			if(alreadyExists)
				throw new IllegalArgumentException("User already has this skill: " + skill.getSkillName());
			UserSkill userSkill = new UserSkill();
			userSkill.setSkill(skill);
			userSkill.setUser(user);
			userSkill.setLevel(1);
			user.getUserSkills().add(userSkill);
			userSkillRepository.save(userSkill);
			userRepository.save(user);
		}

		return user.getUserSkills();
	}
	
	public UserSkillDTO updateUserSkillLevel(UpdateUserSkillLevelDTO updateUserSkillLevelDTO) {
	    UserSkill userSkill = userSkillRepository.findById(updateUserSkillLevelDTO.getUserSkillId())
	            .orElseThrow(() -> new EntityNotFoundException("No skill association found with id: " + updateUserSkillLevelDTO.getUserSkillId()));	    
	    userSkill.setLevel(updateUserSkillLevelDTO.getLevel());
	    userSkillRepository.save(userSkill);
	    return UserSkillMapper.INSTANCE.toUserSkillDTO(userSkill);
	}


	public UserSkillDTO deleteUserSkillById(Integer id) {
		UserSkill userSkill = userSkillRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No skill association found with id: " + id));
		userSkillRepository.deleteById(id);
		return UserSkillMapper.INSTANCE.toUserSkillDTO(userSkill);
	}

}
