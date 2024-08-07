package br.com.neki.sistema_skill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.AssignExistingSkillDTO;
import br.com.neki.sistema_skill.DTOs.CreateAndAssignSkillDTO;
import br.com.neki.sistema_skill.DTOs.CreateSkillDTO;
import br.com.neki.sistema_skill.DTOs.SkillDTO;
import br.com.neki.sistema_skill.DTOs.UserSkillDTO;
import br.com.neki.sistema_skill.services.SkillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	SkillService skillService;
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<CreateSkillDTO> save(@RequestBody @Valid CreateSkillDTO createSkillDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.save(createSkillDTO));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping("/create-add")
	public ResponseEntity<CreateSkillDTO> saveAndAddToUser(@RequestBody @Valid CreateAndAssignSkillDTO createAndAssignSkillDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.saveAndAddToUser(createAndAssignSkillDTO));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping("/add-existing")
    public ResponseEntity<UserSkillDTO> addExistingSkillToUser(@RequestBody @Valid AssignExistingSkillDTO assignExistingSkillDTO) {
        skillService.addExistingSkillToUser(assignExistingSkillDTO);
        return ResponseEntity.status(HttpStatus.OK).body(skillService.addExistingSkillToUser(assignExistingSkillDTO));
    }
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<SkillDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(skillService.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(skillService.deleteUserSkillById(id));
	}
	
}
