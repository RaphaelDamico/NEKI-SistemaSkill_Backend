package br.com.neki.sistema_skill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.CriaUsuarioDTO;
import br.com.neki.sistema_skill.DTOs.UsuarioDTO;
import br.com.neki.sistema_skill.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<CriaUsuarioDTO> save(@RequestBody @Valid CriaUsuarioDTO criaUsuarioDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(criaUsuarioDTO));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
	}
}
