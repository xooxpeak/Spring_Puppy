package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper instance = Mappers.getMapper(UserMapper.class);
	
	UserEntity userDTOToUserEntity(UserDTO userDTO);
}
