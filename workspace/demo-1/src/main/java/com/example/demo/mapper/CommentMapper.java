package com.example.demo.mapper;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper instance = Mappers.getMapper(CommentMapper.class);

    // Entity에서 DTO로 매핑
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "board.id", target = "boardId")
    @Mapping(target = "commentDate", dateFormat = "yyyy.MM.dd HH:mm")
    CommentEntity commentToEntity(CommentDTO commentDTO);


    // DTO에서 Entity로 매핑
//    @Mapping(source = "userId", target = "user.id")
 //   @Mapping(source = "boardId", target = "board.id")
    CommentDTO commentToDto(CommentEntity commentEntity);

}
