package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {   // JpaRepository<Entity 클래스, PK 타입>
	// 사용자의 이름이랑 이메일만 가져오기
//	UserEntity findByEmail(String email);
	UserMapping findByEmail(String email);  // 일부 컬럼만 가져오기 맞나 확인
}
