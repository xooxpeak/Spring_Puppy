package com.example.demo.mapper;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T10:43:18+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity userDTOToUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userDTO.getId() );
        userEntity.userId( userDTO.getUserId() );
        userEntity.password( userDTO.getPassword() );
        userEntity.name( userDTO.getName() );
        userEntity.birth( userDTO.getBirth() );
        userEntity.email( userDTO.getEmail() );
        userEntity.addr1( userDTO.getAddr1() );
        userEntity.addr2( userDTO.getAddr2() );
        userEntity.kakaoId( userDTO.getKakaoId() );

        return userEntity.build();
    }
}
