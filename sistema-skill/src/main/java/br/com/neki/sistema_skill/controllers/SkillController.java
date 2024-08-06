package br.com.neki.sistema_skill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.AtribuiSkillExistenteDTO;
import br.com.neki.sistema_skill.DTOs.CriaEAtribuiSkillDTO;
import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
import br.com.neki.sistema_skill.DTOs.SkillDTO;
import br.com.neki.sistema_skill.entities.UserSkill;
import br.com.neki.sistema_skill.services.SkillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	SkillService skillService;
	
	@PostMapping
	public ResponseEntity<CriaSkillDTO> save(@RequestBody @Valid CriaSkillDTO criaSkillDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.save(criaSkillDTO));
	}
	
	@PostMapping("/cria-adiciona")
	public ResponseEntity<CriaSkillDTO> saveAndAddToUser(@RequestBody @Valid CriaEAtribuiSkillDTO criaEAtribuiSkillDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.saveAndAddToUser(criaEAtribuiSkillDTO));
	}
	
	@PostMapping("/adiciona-existente")
    public ResponseEntity<UserSkill> addExistingSkillToUser(@RequestBody @Valid AtribuiSkillExistenteDTO atribuiSkillExistenteDTO) {
        skillService.addExistingSkillToUser(atribuiSkillExistenteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(skillService.addExistingSkillToUser(atribuiSkillExistenteDTO));
    }
	
	@GetMapping
	public ResponseEntity<List<SkillDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(skillService.findAll());
	}
}
