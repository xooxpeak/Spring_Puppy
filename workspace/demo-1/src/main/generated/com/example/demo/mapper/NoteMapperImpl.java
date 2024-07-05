package com.example.demo.mapper;

import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.NoteEntity;
import com.example.demo.entity.PuppyEntity;
import java.sql.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-05T12:41:54+0900",
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

        noteEntity.puppy( noteDTOToPuppyEntity( noteDTO ) );
        noteEntity.id( noteDTO.getId() );
        noteEntity.noteDate( noteDTO.getNoteDate() );
        noteEntity.meal( noteDTO.getMeal() );
        noteEntity.poopFrequency( noteDTO.getPoopFrequency() );
        noteEntity.poopCondition( noteDTO.getPoopCondition() );
        noteEntity.mood( noteDTO.getMood() );
        noteEntity.daily( noteDTO.getDaily() );
        noteEntity.puppyId( noteDTO.getPuppyId() );

        return noteEntity.build();
    }

    @Override
    public NoteDTO noteToDTO(NoteEntity noteEntity) {
        if ( noteEntity == null ) {
            return null;
        }

        NoteDTO.NoteDTOBuilder noteDTO = NoteDTO.builder();

        noteDTO.puppyId( noteEntityPuppyId( noteEntity ) );
        noteDTO.puppyName( noteEntityPuppyPuppyName( noteEntity ) );
        noteDTO.id( noteEntity.getId() );
        if ( noteEntity.getNoteDate() != null ) {
            noteDTO.noteDate( new Date( noteEntity.getNoteDate().getTime() ) );
        }
        noteDTO.meal( noteEntity.getMeal() );
        noteDTO.poopFrequency( noteEntity.getPoopFrequency() );
        noteDTO.poopCondition( noteEntity.getPoopCondition() );
        noteDTO.mood( noteEntity.getMood() );
        noteDTO.daily( noteEntity.getDaily() );

        return noteDTO.build();
    }

    protected PuppyEntity noteDTOToPuppyEntity(NoteDTO noteDTO) {
        if ( noteDTO == null ) {
            return null;
        }

        PuppyEntity.PuppyEntityBuilder puppyEntity = PuppyEntity.builder();

        puppyEntity.id( noteDTO.getPuppyId() );

        return puppyEntity.build();
    }

    private Long noteEntityPuppyId(NoteEntity noteEntity) {
        if ( noteEntity == null ) {
            return null;
        }
        PuppyEntity puppy = noteEntity.getPuppy();
        if ( puppy == null ) {
            return null;
        }
        Long id = puppy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String noteEntityPuppyPuppyName(NoteEntity noteEntity) {
        if ( noteEntity == null ) {
            return null;
        }
        PuppyEntity puppy = noteEntity.getPuppy();
        if ( puppy == null ) {
            return null;
        }
        String puppyName = puppy.getPuppyName();
        if ( puppyName == null ) {
            return null;
        }
        return puppyName;
    }
}
