package com.example.demo.repository;

import com.example.demo.entity.PuppyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.NoteEntity;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    List<NoteEntity> findAllByPuppyIn(List<PuppyEntity> puppies);
}
