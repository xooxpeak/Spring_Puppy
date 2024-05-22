package com.example.demo.mapper;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-22T10:32:43+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public BoardEntity boardToEntity(BoardDTO boardDTO) {
        if ( boardDTO == null ) {
            return null;
        }

        BoardEntity.BoardEntityBuilder boardEntity = BoardEntity.builder();

        boardEntity.id( boardDTO.getId() );
        boardEntity.userId( boardDTO.getUserId() );
        boardEntity.title( boardDTO.getTitle() );
        boardEntity.content( boardDTO.getContent() );
        boardEntity.boardDate( boardDTO.getBoardDate() );
        boardEntity.views( boardDTO.getViews() );
        boardEntity.userLike( boardDTO.getUserLike() );

        return boardEntity.build();
    }

    @Override
    public BoardDTO boardToDTO(BoardEntity boardEntity) {
        if ( boardEntity == null ) {
            return null;
        }

        BoardDTO.BoardDTOBuilder boardDTO = BoardDTO.builder();

        boardDTO.id( boardEntity.getId() );
        boardDTO.userId( boardEntity.getUserId() );
        boardDTO.title( boardEntity.getTitle() );
        boardDTO.content( boardEntity.getContent() );
        boardDTO.boardDate( boardEntity.getBoardDate() );
        boardDTO.views( boardEntity.getViews() );
        boardDTO.userLike( boardEntity.getUserLike() );

        return boardDTO.build();
    }
}
