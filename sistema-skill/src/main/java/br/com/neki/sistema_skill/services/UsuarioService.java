package br.com.neki.sistema_skill.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill.DTOs.CriaUsuarioDTO;
import br.com.neki.sistema_skill.DTOs.UsuarioDTO;
import br.com.neki.sistema_skill.entities.Usuario;
import br.com.neki.sistema_skill.exceptions.EntityNotFoundException;
import br.com.neki.sistema_skill.mappers.UsuarioMapper;
import br.com.neki.sistema_skill.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	private final PasswordEncoder senhaCriptografada = new BCryptPasswordEncoder();
	
	public CriaUsuarioDTO save(CriaUsuarioDTO createUsuarioDTO) {
		Usuario usuarioSave = new Usuario(createUsuarioDTO);
		usuarioSave.setSenha(senhaCriptografada.encode(createUsuarioDTO.getSenha()));
		usuarioRepository.save(usuarioSave);
		return UsuarioMapper.INSTANCE.toCriaUsuarioDTO(usuarioSave);
	}
	
	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		if(usuarios.isEmpty())
			throw new NoSuchElementException("Nenhum usuário encontrado!");
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for(Usuario usuario : usuarios) {
			usuariosDTO.add(UsuarioMapper.INSTANCE.toUsuarioDTO(usuario));
		}
		return usuariosDTO;
	}
	
	public UsuarioDTO findById(Integer id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado com o id: " + id));
		return UsuarioMapper.INSTANCE.toUsuarioDTO(usuario);
	}
	
}
