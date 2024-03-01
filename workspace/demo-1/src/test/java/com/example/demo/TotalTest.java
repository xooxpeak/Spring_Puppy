package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.PuppyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.PuppyRepository;
import com.example.demo.repository.UserMapping;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

/*@Autowired
MockMvc mockMvc;*/

@SpringBootTest
public class TotalTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PuppyRepository puppyRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	// 잘 가져오는지 테스트
	@Test  
	public void test() {
		userRepository.findAll();
	}
	
	// 회원가입 
	@Test
	@Transactional   // 테스트 코드에서 작성한 더미 데이터를 자동으로 삭제해주는 기능
	public void signUp() {
		// UserEntity userEntity;
		// userEntity.setEmail();    원래 이렇게 쓰던 것을
		
		userRepository.save(UserEntity.builder()    // builder를 사용해 가독성 좋은 코드 작성
				.password("12345")
				.name("이름")
				.birth(new Date(1l))
				.email("test1@test1.com")
				.phone("4")
				.addr1("1")
				.addr2("2")
				.build()  // build 메서드 호출하여 최종 UserEntity 객체 생성
				);
		
	}
	
	
	// 로그인
	@Test
	@DisplayName("로그인테스트 (이메일, 패스워드) : 성공")
	@Transactional
	public void login() {
		
		/*
		 * UserEntity userEntity = UserEntity.builder() .email("test1@test1.com")
		 * .password("12345") .build();
		 */
		
		UserEntity userEntity1 = userRepository.findByEmail("test1@test1.com");

		
		// 로그인 한 후 모든 entity에 데이터 넣기
		puppyRepository.save(PuppyEntity.builder()
				.puppyName("강아지1")
				.gender("남")
				.neutering(true)
				.puppyBirth(new Date(1l))
				.breed("믹스")
				.allergy("없음")
				.build()
				);
		
		boardRepository.save(BoardEntity.builder()
				.title("글1")
				.content("내용1")
				.build()
				);
		
	}
	
	 
	
	// 사용자의 이름이랑 이메일만 가져오기 -> jpa 특정 칼럼만 가져오기 검색 -> 인터페이스 사용 https://imdandyu.tistory.com/36
	// findbyemail();
	
	
	
	// 사용자 이메일에 맞는 정보 가져오기
	// 저장한 데이터가 test1@test1.com 이면 findbyEmail("test1@test1.com") -> 정보 가져오기 
	@Test
	@Transactional
	public void testFindByEmail() {
		String emailToSearch = "test1@test1.com";   // 검색할 이메일 주소
		
		UserMapping user = userRepository.findByEmail(emailToSearch);   // findByEmail 호출
		
//		assertNotNull(users);  // 조회된 사용자 목록이 null 확인
//		assertFalse(users.isEmpty());  // 비어있지 않은지 확인   ->  if문으로 변경(?)
		
//		UserMapping user = users.get(0);  // 조회된 사용자 목록에서 첫 번째 사용자
//		assertEquals(emailToSearch, user.getEmail());  // 이메일이 일치하는지 확인
//		assertNotNull(user.getName());  // 첫 번째 사용자의 이름이 null이 아닌지 확인
	}
	
	
	// 마지막에 유저entity 가져오면서 모든 데이터들이 가져와 졌는지 확인
	

}
