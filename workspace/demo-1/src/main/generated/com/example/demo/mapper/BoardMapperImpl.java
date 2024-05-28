package com.example.demo.mapper;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-28T22:17:59+0900",
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

        boardDTO.strUserId( boardEntityUserUserId( boardEntity ) );
        boardDTO.id( boardEntity.getId() );
        boardDTO.userId( boardEntity.getUserId() );
        boardDTO.title( boardEntity.getTitle() );
        boardDTO.content( boardEntity.getContent() );
        boardDTO.boardDate( boardEntity.getBoardDate() );
        boardDTO.views( boardEntity.getViews() );
        boardDTO.userLike( boardEntity.getUserLike() );

        return boardDTO.build();
    }

    @Override
    public BoardDTO boardToDTOWithAuthor(BoardEntity boardEntity, boolean isAuthor) {
        if ( boardEntity == null ) {
            return null;
        }

        BoardDTO.BoardDTOBuilder boardDTO = BoardDTO.builder();

        if ( boardEntity != null ) {
            boardDTO.id( boardEntity.getId() );
            boardDTO.userId( boardEntity.getUserId() );
            boardDTO.title( boardEntity.getTitle() );
            boardDTO.content( boardEntity.getContent() );
            boardDTO.boardDate( boardEntity.getBoardDate() );
            boardDTO.views( boardEntity.getViews() );
            boardDTO.userLike( boardEntity.getUserLike() );
        }
        boardDTO.isAuthor( isAuthor );

        return boardDTO.build();
    }

    private String boardEntityUserUserId(BoardEntity boardEntity) {
        if ( boardEntity == null ) {
            return null;
        }
        UserEntity user = boardEntity.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
