package com.example.demo.mapper;

import com.example.demo.dto.StoreDTO;
import com.example.demo.entity.StoreEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-02T22:21:44+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreEntity storeToEntity(StoreDTO storeDTO) {
        if ( storeDTO == null ) {
            return null;
        }

        StoreEntity storeEntity = new StoreEntity();

        storeEntity.setId( storeDTO.getId() );
        storeEntity.setStoreName( storeDTO.getStoreName() );
        storeEntity.setManagerName( storeDTO.getManagerName() );
        storeEntity.setStorePhone( storeDTO.getStorePhone() );
        storeEntity.setStoreAddr1( storeDTO.getStoreAddr1() );
        storeEntity.setStoreAddr2( storeDTO.getStoreAddr2() );

        return storeEntity;
    }

    @Override
    public StoreDTO storeToDTO(StoreEntity storeEntity) {
        if ( storeEntity == null ) {
            return null;
        }

        StoreDTO.StoreDTOBuilder storeDTO = StoreDTO.builder();

        storeDTO.id( storeEntity.getId() );
        storeDTO.storeName( storeEntity.getStoreName() );
        storeDTO.managerName( storeEntity.getManagerName() );
        storeDTO.storePhone( storeEntity.getStorePhone() );
        storeDTO.storeAddr1( storeEntity.getStoreAddr1() );
        storeDTO.storeAddr2( storeEntity.getStoreAddr2() );

        return storeDTO.build();
    }
}
