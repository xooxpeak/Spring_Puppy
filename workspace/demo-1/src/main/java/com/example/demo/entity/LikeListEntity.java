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

	// BoardEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "board_id")
	private BoardEntity board;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

//	// StoreEntity와 다대일 관계 & 조인 
//	@ManyToOne
//	@JoinColumn(name = "id", insertable = false, updatable = false)
//	private StoreEntity store;
	
}
