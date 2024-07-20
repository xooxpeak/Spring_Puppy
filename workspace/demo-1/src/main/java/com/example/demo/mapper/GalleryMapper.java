package com.example.demo.mapper;

import com.example.demo.dto.GalleryDTO;
import com.example.demo.entity.GalleryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GalleryMapper {

    GalleryMapper instance = Mappers.getMapper(GalleryMapper.class);

    // GalleryEntity를 GalleryDTO로 맵핑
//    @Mapping(source = "gallImg", target = "gallImg")
    GalleryDTO galleryToDTO(GalleryEntity galleryEntity);
    GalleryEntity galleryToEntity(GalleryDTO galleryDTO);


}
