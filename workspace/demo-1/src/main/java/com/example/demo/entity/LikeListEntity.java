package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "LikeList")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeListEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")   
	private Long userId;   
	
//	@Column(name = "board_id")  // 확인 필요
//	private Long boardId;
	
	// BoardEntity와 다대일 관계
	@ManyToOne  
	private BoardEntity board;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private UserEntity user;
	
//	// StoreEntity와 다대일 관계 & 조인 
//	@ManyToOne
//	@JoinColumn(name = "id", insertable = false, updatable = false)
//	private StoreEntity store;
	
}
