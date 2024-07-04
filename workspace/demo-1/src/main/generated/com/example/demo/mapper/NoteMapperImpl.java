package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.NoteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T09:49:44+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteEntity noteToEntity(NoteDTO noteDTO) {
        if ( noteDTO == null ) {
            return null;
        }

        NoteEntity.NoteEntityBuilder noteEntity = NoteEntity.builder();

        noteEntity.id( noteDTO.getId() );
        noteEntity.noteDate( noteDTO.getNoteDate() );
        noteEntity.meal( noteDTO.getMeal() );
        noteEntity.poopFrequency( noteDTO.getPoopFrequency() );
        noteEntity.poopCondition( noteDTO.getPoopCondition() );
        noteEntity.mood( noteDTO.getMood() );
        noteEntity.daily( noteDTO.getDaily() );

        return noteEntity.build();
    }

    @Override
    public NoteDTO noteToDTO(NoteEntity noteEntity) {
        if ( noteEntity == null ) {
            return null;
        }

        NoteDTO.NoteDTOBuilder noteDTO = NoteDTO.builder();

        noteDTO.id( noteEntity.getId() );
        noteDTO.noteDate( noteEntity.getNoteDate() );
        noteDTO.meal( noteEntity.getMeal() );
        noteDTO.poopFrequency( noteEntity.getPoopFrequency() );
        noteDTO.poopCondition( noteEntity.getPoopCondition() );
        noteDTO.mood( noteEntity.getMood() );
        noteDTO.daily( noteEntity.getDaily() );

        return noteDTO.build();
    }
}
