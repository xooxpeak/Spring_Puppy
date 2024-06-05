package com.example.demo.mapper;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.CommentEntity;
import java.sql.Date;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:43:18+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity commentToEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        if ( commentDTO.getCommentDate() != null ) {
            commentEntity.setCommentDate( new Date( commentDTO.getCommentDate().getTime() ) );
        }
        commentEntity.setId( commentDTO.getId() );
        commentEntity.setUserId( commentDTO.getUserId() );
        commentEntity.setComment( commentDTO.getComment() );

        return commentEntity;
    }

    @Override
    public CommentDTO commentToDto(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        CommentDTO.CommentDTOBuilder commentDTO = CommentDTO.builder();

        commentDTO.id( commentEntity.getId() );
        commentDTO.userId( commentEntity.getUserId() );
        commentDTO.commentDate( commentEntity.getCommentDate() );
        commentDTO.comment( commentEntity.getComment() );

        return commentDTO.build();
    }
}
