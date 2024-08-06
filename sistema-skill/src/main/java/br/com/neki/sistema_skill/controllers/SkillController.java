package br.com.neki.sistema_skill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.CriaSkillDTO;
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
}
