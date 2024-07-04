package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteMapper instance = Mappers.getMapper(NoteMapper.class);

    @Mapping(source = "puppyId", target = "puppy.id")
    NoteEntity noteToEntity(NoteDTO noteDTO);

    @Mapping(source = "puppy.id", target = "puppyId")
    NoteDTO noteToDTO(NoteEntity noteEntity);

}
