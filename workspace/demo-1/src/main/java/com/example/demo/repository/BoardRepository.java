package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    @Modifying
    @Transactional
    @Query("update board b set b.views = b.views + 1 where b.id = :id")
    int updateView(@Param("id") Long id);

}
