package br.com.neki.sistema_skill.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill.DTOs.User.CreateUserDTO;
import br.com.neki.sistema_skill.DTOs.User.UserDetailsDTO;
import br.com.neki.sistema_skill.entities.User;
import br.com.neki.sistema_skill.enums.AccessType;
import br.com.neki.sistema_skill.exceptions.EntityNotFoundException;
import br.com.neki.sistema_skill.exceptions.UsernameAlreadyExistsException;
import br.com.neki.sistema_skill.mappers.UserMapper;
import br.com.neki.sistema_skill.records.JwtTokenRecord;
import br.com.neki.sistema_skill.records.LoginCredentialsRecord;
import br.com.neki.sistema_skill.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	private final PasswordEncoder encryptedPassword = new BCryptPasswordEncoder();

	public CreateUserDTO createSimpleUser(CreateUserDTO createUserDTO) {
		Optional<User> existingUser = userRepository.findByUsername(createUserDTO.getUsername());
		if (existingUser.isPresent())
			throw new UsernameAlreadyExistsException("The username" + createUserDTO.getUsername() + " already exists.");
		User userSave = new User(createUserDTO);
		userSave.setPassword(encryptedPassword.encode(createUserDTO.getPassword()));
		userSave.setAccessType(AccessType.ROLE_SIMPLE);
		userRepository.save(userSave);
		return UserMapper.INSTANCE.toCreateUserDTO(userSave);
	}

	public JwtTokenRecord authenticateUser(LoginCredentialsRecord loginCredentialsRecord) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginCredentialsRecord.username(), loginCredentialsRecord.password());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		User userDetails = (User) authentication.getPrincipal();
		return new JwtTokenRecord(jwtTokenService.generateToken(userDetails), userDetails.getUserId());
	}

	public List<UserDetailsDTO> findAll() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty())
			throw new NoSuchElementException("No user found!");
		List<UserDetailsDTO> userDetailsDTO = new ArrayList<>();
		for (User user : users) {
			userDetailsDTO.add(UserMapper.INSTANCE.toUserDTO(user));
		}
		return userDetailsDTO;
	}

	public UserDetailsDTO findById(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No user found with id: " + id));
		return UserMapper.INSTANCE.toUserDTO(user);
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("Incorrect username or password!"));
		if (user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		return user;
	}

}
