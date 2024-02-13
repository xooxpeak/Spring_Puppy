package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LikeListEntity;

@Repository
public interface LikeListRepository extends JpaRepository<LikeListEntity, Long> {

}
