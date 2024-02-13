package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PuppyEntity;

@Repository
public interface PuppyRepository extends JpaRepository<PuppyEntity, Long> {

}
