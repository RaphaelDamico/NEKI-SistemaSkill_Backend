package br.com.neki.sistema_skill.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.User.CreateUserDTO;
import br.com.neki.sistema_skill.DTOs.User.UserDetailsDTO;
import br.com.neki.sistema_skill.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<CreateUserDTO> createSimpleUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createSimpleUser(createUserDTO));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDetailsDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDetailsDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
}
