package com.example.demo.mapper;

import com.example.demo.dto.PuppyDTO;
import com.example.demo.entity.PuppyEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T12:51:24+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class PuppyMapperImpl implements PuppyMapper {

    @Override
    public PuppyEntity puppyDTOToPuppyEntity(PuppyDTO puppyDTO) {
        if ( puppyDTO == null ) {
            return null;
        }

        PuppyEntity.PuppyEntityBuilder puppyEntity = PuppyEntity.builder();

        puppyEntity.id( puppyDTO.getId() );
        puppyEntity.puppyName( puppyDTO.getPuppyName() );
        puppyEntity.gender( puppyDTO.getGender() );
        puppyEntity.neutering( puppyDTO.getNeutering() );
        puppyEntity.puppyBirth( puppyDTO.getPuppyBirth() );
        puppyEntity.breed( puppyDTO.getBreed() );
        puppyEntity.allergy( puppyDTO.getAllergy() );
        puppyEntity.personality( puppyDTO.getPersonality() );
        puppyEntity.introduction( puppyDTO.getIntroduction() );
        puppyEntity.profileImg( puppyDTO.getProfileImg() );

        return puppyEntity.build();
    }

    @Override
    public PuppyDTO puppyEntityToPuppyDTO(PuppyEntity puppyEntity) {
        if ( puppyEntity == null ) {
            return null;
        }

        PuppyDTO.PuppyDTOBuilder puppyDTO = PuppyDTO.builder();

        puppyDTO.id( puppyEntity.getId() );
        puppyDTO.puppyName( puppyEntity.getPuppyName() );
        puppyDTO.gender( puppyEntity.getGender() );
        puppyDTO.neutering( puppyEntity.getNeutering() );
        puppyDTO.puppyBirth( puppyEntity.getPuppyBirth() );
        puppyDTO.breed( puppyEntity.getBreed() );
        puppyDTO.allergy( puppyEntity.getAllergy() );
        puppyDTO.personality( puppyEntity.getPersonality() );
        puppyDTO.introduction( puppyEntity.getIntroduction() );
        puppyDTO.profileImg( puppyEntity.getProfileImg() );

        return puppyDTO.build();
    }
}
