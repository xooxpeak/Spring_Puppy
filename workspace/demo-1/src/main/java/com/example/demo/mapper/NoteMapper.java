package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteMapper instance = Mappers.getMapper(NoteMapper.class);

    NoteEntity noteDTOToNoteEntity(NoteDTO noteDTO);
}
