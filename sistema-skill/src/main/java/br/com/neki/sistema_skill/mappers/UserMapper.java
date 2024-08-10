package br.com.neki.sistema_skill.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.neki.sistema_skill.DTOs.User.CreateUserDTO;
import br.com.neki.sistema_skill.DTOs.User.UserDetailsDTO;
import br.com.neki.sistema_skill.entities.User;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDetailsDTO toUserDTO(User user);
	CreateUserDTO toCreateUserDTO(User user);
	User toEntity(UserDetailsDTO userDetailsDTO);
	User toEntity(CreateUserDTO createUserDTO);
}
