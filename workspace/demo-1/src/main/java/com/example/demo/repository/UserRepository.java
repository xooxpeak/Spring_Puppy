package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {   // JpaRepository<Entity 클래스, PK 타입>
	// 사용자의 이름이랑 이메일만 가져오기
	// UserEntity findByEmail(String email);
	// UserMapping findByEmail(String email);
	
	// 아이디 중복 검사
	// 주어진 아이디(userId)와 일치하는 사용자가 데이터베이스에 존재하는지 확인하는 메서드
	// existsBy : 해당 엔터티에 대해 특정 조건을 만족하는 레코드가 존재하는지 확인해주는 접두어
	boolean existsByUserId(String userId);
	
	// 회원가입
	// UserEntity save(UserEntity userEntity);

//	@Query("SELECT ur.userId, ur.roleId FROM UserRole ur WHERE ur.userId = :userId")
//	Optional<List<Object[]>> findUserRolesByUserId(@Param("userId") String userId);

//	Optional<List<Object[]>> findByUserId(String userId);

//	Optional<UserEntity> findByUserId(String userId);

	UserEntity findByUserId(String userId);

	Optional<UserEntity> findByKakaoId(String kakaoId);

//	Optional<UserEntity> findByUserId(String userId);

}
