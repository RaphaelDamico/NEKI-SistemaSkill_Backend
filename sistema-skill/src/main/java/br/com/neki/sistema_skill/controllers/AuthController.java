package br.com.neki.sistema_skill.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill.DTOs.CreateUserDTO;
import br.com.neki.sistema_skill.records.JwtTokenRecord;
import br.com.neki.sistema_skill.records.LoginCredentialsRecord;
import br.com.neki.sistema_skill.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    
    @PostMapping("/signin")
    public ResponseEntity<JwtTokenRecord> signin(@RequestBody LoginCredentialsRecord credenciaisLoginRecord) {
        JwtTokenRecord jwtToken = userService.authenticateUser(credenciaisLoginRecord);
        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<CreateUserDTO> signup(@RequestBody CreateUserDTO userCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createSimpleUser(userCreateDTO));
    }

}