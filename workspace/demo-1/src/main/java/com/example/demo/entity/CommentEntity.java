package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity(name = "comment")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "board_id")
	private Long boardId;

	@Column(name = "comment_date", nullable = false)
	private Date commentDate;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)  // 작성하고 추가 할 수 없다.
	private UserEntity user;
	
	// BoardEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "board_id", insertable = false, updatable = false)
	private BoardEntity board;

}
