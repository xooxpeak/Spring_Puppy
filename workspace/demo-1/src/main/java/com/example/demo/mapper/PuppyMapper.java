package com.example.demo.mapper;

import com.example.demo.dto.PuppyDTO;
import com.example.demo.entity.PuppyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PuppyMapper {

    PuppyMapper instance = Mappers.getMapper(PuppyMapper.class);

    // PuppyDTO -> PuppyEntity
    @Mapping(source = "userId", target = "user.id")
    PuppyEntity puppyDTOToPuppyEntity(PuppyDTO puppyDTO);

    // PuppyEntity -> PuppyDTO
    @Mapping(source = "user.id", target = "userId")
    PuppyDTO puppyEntityToPuppyDTO(PuppyEntity puppyEntity);
}
