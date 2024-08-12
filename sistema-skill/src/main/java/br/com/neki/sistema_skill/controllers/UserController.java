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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Operation(summary = "Este método cadastra um usuário com acesso restrito, garantindo que o nome de usuário seja único e retornando o username e o password criptografado.", method = "POST")
	@PostMapping("/signup")
    public ResponseEntity<CreateUserDTO> signup(@RequestBody @Valid CreateUserDTO userCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createSimpleUser(userCreateDTO));
    }
	
	@Operation(summary = "Este método captura todos os usuários cadastrados, retornando o userId, username e suas listas de skills.", method = "GET")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDetailsDTO>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@Operation(summary = "Este método captura o usuário cadastrado pelo seu ID, retornando o userId, username e sua lista de skills.", method = "GET")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDetailsDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
	}
}
