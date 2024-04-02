package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity(name = "comment")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "commentId")
//	private Long commentId;
	
//	@Column(name = "board_id")
//	private Long boardId;

	@Column(name = "user_id")  
	private Long userId;   
	
	@Column(name = "comment_date", nullable = false)
// TODO : 지원하지 않는다고 하니 확인하기	
//	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)  // 작성하고 추가 할 수 없다.
	private UserEntity user;
	
	// BoardEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "board_id", insertable = false, updatable = false)  // 추가함
	private BoardEntity board;

}
