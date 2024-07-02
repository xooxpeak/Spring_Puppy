package com.example.demo.mapper;

import com.example.demo.dto.StoreDTO;
import com.example.demo.entity.StoreEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper instance = Mappers.getMapper(StoreMapper.class);

    // StoreDTO -> StoreEntity
    StoreEntity storeToEntity(StoreDTO storeDTO);

    // StoreEntity -> StoreDTO
    StoreDTO storeToDTO(StoreEntity storeEntity);
}
