package com.example.demo.repository;

import com.example.demo.entity.PuppyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuppyRepository extends JpaRepository<PuppyEntity, Long> {

    List<PuppyEntity> findByUserId(Long userId);
}
