package com.example.demo.mapper;

import com.example.demo.dto.GalleryDTO;
import com.example.demo.entity.GalleryEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-06T12:08:41+0900",
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

        galleryDTO.gallImg( galleryEntity.getGallImg() );
        galleryDTO.gallExtension( galleryEntity.getGallExtension() );
        galleryDTO.id( galleryEntity.getId() );
        galleryDTO.noteId( galleryEntity.getNoteId() );
        galleryDTO.gallDate( galleryEntity.getGallDate() );

        return galleryDTO.build();
    }
}
