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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.Skill.AssignExistingSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.CreateAndAssignSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.CreateSkillDTO;
import br.com.neki.sistema_skill.DTOs.Skill.SkillDTO;
import br.com.neki.sistema_skill.DTOs.UserSkill.UpdateUserSkillLevelDTO;
import br.com.neki.sistema_skill.DTOs.UserSkill.UserSkillDTO;
import br.com.neki.sistema_skill.entities.UserSkill;
import br.com.neki.sistema_skill.services.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	SkillService skillService;
	
	@Operation(summary = "Este método cadastra uma skill no banco, garantindo que o nome da skill seja único, que possua descrição e que além de possuir url, ele seja válido, retornando o nome da skill, a descrição, e o url da imagem. o level da skill será adicionado pelo usuário em sua lista de skills.", method = "POST")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<CreateSkillDTO> save(@RequestBody @Valid CreateSkillDTO createSkillDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.save(createSkillDTO));
	}
	
	@Operation(summary = "Este método cadastra uma skill no banco e a atribui ao usuário que a cadastrou, garantindo que o nome da skill seja único, que possua descrição e que além de possuir url, ele seja válido. Além disso, neste método, o level é requisitado, devendo ser entre 1 e 5, retornando o nome da skill, a descrição, o url da imagem e o level da skill na lista de skills do usuário.", method = "POST")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping("/create-add")
	public ResponseEntity<CreateSkillDTO> saveAndAddToUser(@RequestBody @Valid CreateAndAssignSkillDTO createAndAssignSkillDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.saveAndAddToUser(createAndAssignSkillDTO));
	}
	
	@Operation(summary = "Este método adiciona uma skill existente à lista de skills do usuário. Ao passar o ID do usuário e o ID da skill, ela é adicionada à lista, retornando o userSkillId, a skill adicionada com seu ID, nome, descrição, url da imagem e o level setado como 1, pressupondo que ao adicionar a skill em sua lista o usuário já possua um nível básico de conhecimento da skill.", method = "POST")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping("/add-existing")
    public ResponseEntity<List<UserSkill>> addExistingSkillToUser(@RequestBody @Valid List<AssignExistingSkillDTO> listAssignExistingSkillDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.addExistingSkillToUser(listAssignExistingSkillDTO));
    }
	
	@Operation(summary = "Este método captura todas as skills cadastradas no banco, retornando o skillId, skillName, descrição e o url da imagem da skill.", method = "GET")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<SkillDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(skillService.findAll());
	}
	
	@Operation(summary = "Este método atualiza o level da skill cadastrada na lista de skills do usuário, garantindo que seu level seja no mínimo 1 e no máximo 5 e retornando as informações da skill com o level atualizado.", method = "PUT")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PutMapping("/level")
	public ResponseEntity<UserSkillDTO> updateUserSkillLevel(@RequestBody @Valid UpdateUserSkillLevelDTO updateUserSkillLevelDTO) {
	    return ResponseEntity.status(HttpStatus.OK).body(skillService.updateUserSkillLevel(updateUserSkillLevelDTO));
	}

	@Operation(summary = "Este método deleta a skill da lista de skills do usuário pelo seu userSkillID", method = "DELETE")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(skillService.deleteUserSkillById(id));
	}
	
}
