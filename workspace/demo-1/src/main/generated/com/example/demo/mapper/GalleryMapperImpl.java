package com.example.demo.mapper;

import com.example.demo.dto.GalleryDTO;
import com.example.demo.entity.GalleryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T13:44:27+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class GalleryMapperImpl implements GalleryMapper {

    @Override
    public GalleryDTO galleryToDTO(GalleryEntity galleryEntity) {
        if ( galleryEntity == null ) {
            return null;
        }

        GalleryDTO.GalleryDTOBuilder galleryDTO = GalleryDTO.builder();

        galleryDTO.id( galleryEntity.getId() );
        galleryDTO.gallDate( galleryEntity.getGallDate() );
        galleryDTO.gallImg( galleryEntity.getGallImg() );
        galleryDTO.gallExtension( galleryEntity.getGallExtension() );

        return galleryDTO.build();
    }

    @Override
    public GalleryEntity galleryToEntity(GalleryDTO galleryDTO) {
        if ( galleryDTO == null ) {
            return null;
        }

        GalleryEntity.GalleryEntityBuilder galleryEntity = GalleryEntity.builder();

        galleryEntity.id( galleryDTO.getId() );
        galleryEntity.gallDate( galleryDTO.getGallDate() );
        galleryEntity.gallImg( galleryDTO.getGallImg() );
        galleryEntity.gallExtension( galleryDTO.getGallExtension() );

        return galleryEntity.build();
    }
}
