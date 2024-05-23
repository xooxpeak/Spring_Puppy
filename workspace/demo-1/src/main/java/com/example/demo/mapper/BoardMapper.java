package com.example.demo.mapper;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")  // 의존성 주입을 위함
public interface BoardMapper {

    BoardMapper instance = Mappers.getMapper(BoardMapper.class);
    
    // Entity에서 DTO로 매핑
    BoardEntity boardToEntity(BoardDTO boardDTO);

    // DTO에서 Entity로 매핑: 클라이언트가 보낸 데이터를 데이터베이스에 저장하기 전에 Entity로 변환
    BoardDTO boardToDTO(BoardEntity boardEntity);

    // 작성자와 동일한지 여부를 포함
//    @Mapping(target = "isAuthor", source = "isAuthor")
    BoardDTO boardToDTOWithAuthor(BoardEntity boardEntity, boolean isAuthor);

}
