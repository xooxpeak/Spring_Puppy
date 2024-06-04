package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LikeListEntity;

import java.util.Optional;

@Repository
public interface LikeListRepository extends JpaRepository<LikeListEntity, Long> {

    Optional<LikeListEntity> findByUserAndBoard(UserEntity user, BoardEntity board);
    void deleteByUserAndBoard(UserEntity user, BoardEntity board);
}
