package com.example.demo.mapper;

import com.example.demo.dto.GalleryDTO;
import com.example.demo.entity.GalleryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GalleryMapper {

    GalleryMapper instance = Mappers.getMapper(GalleryMapper.class);

    //galleryEntity를 galleryDTO로 맵핑
    @Mapping(source = "gallImg", target = "gallImg")
    @Mapping(source = "gallExtension", target = "gallExtension")
    GalleryDTO galleryToDTO(GalleryEntity galleryEntity);
}
