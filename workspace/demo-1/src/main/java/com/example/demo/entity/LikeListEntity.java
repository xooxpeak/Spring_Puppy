package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likelist")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	@JoinColumn(name = "board_id", insertable = false, updatable = false)
	private BoardEntity board;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity user;

//	// StoreEntity와 다대일 관계 & 조인 
//	@ManyToOne
//	@JoinColumn(name = "id", insertable = false, updatable = false)
//	private StoreEntity store;
	
}
