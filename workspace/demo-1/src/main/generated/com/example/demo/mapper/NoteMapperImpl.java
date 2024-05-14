package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.NoteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-17T12:00:24+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteEntity noteDTOToNoteEntity(NoteDTO noteDTO) {
        if ( noteDTO == null ) {
            return null;
        }

        NoteEntity noteEntity = new NoteEntity();

        noteEntity.setId( noteDTO.getId() );
        noteEntity.setNoteDate( noteDTO.getNoteDate() );
        noteEntity.setMeal( noteDTO.getMeal() );
        noteEntity.setPoop( noteDTO.getPoop() );
        noteEntity.setCondition( noteDTO.getCondition() );
        noteEntity.setDaily( noteDTO.getDaily() );

        return noteEntity;
    }
}
