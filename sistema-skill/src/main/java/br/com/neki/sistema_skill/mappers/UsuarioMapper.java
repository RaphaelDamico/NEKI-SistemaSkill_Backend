package br.com.neki.sistema_skill.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill.DTOs.CreateUsuarioDTO;
import br.com.neki.sistema_skill.DTOs.UsuarioDTO;
import br.com.neki.sistema_skill.entities.Usuario;

@Mapper
public interface UsuarioMapper {
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	UsuarioDTO toUsuarioDTO(Usuario usuario);
	CreateUsuarioDTO toCreateUsuarioDTO(Usuario usuario);
	Usuario toEntity(UsuarioDTO usuarioDTO);
	Usuario toEntity(CreateUsuarioDTO createUsuarioDTO);
}
