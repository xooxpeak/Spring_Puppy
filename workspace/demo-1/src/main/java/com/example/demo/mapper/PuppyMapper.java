package com.example.demo.mapper;

import com.example.demo.dto.PuppyDTO;
import com.example.demo.entity.PuppyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PuppyMapper {

    PuppyMapper instance = Mappers.getMapper(PuppyMapper.class);

    // PuppyDTO -> PuppyEntity
    PuppyEntity puppyDTOToPuppyEntity(PuppyDTO puppyDTO);

    // PuppyEntity -> PuppyDTO
    PuppyDTO puppyEntityToPuppyDTO(PuppyEntity puppyEntity);
}
