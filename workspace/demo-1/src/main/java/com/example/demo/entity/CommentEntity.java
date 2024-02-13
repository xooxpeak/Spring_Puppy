package com.example.demo.entity;

import java.sql.Date;

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
	
//	@Column(name = "boardId")
//	private Long boardId;
//	
//	@Column(name = "userId")  
//	private Long userId;   
	
	@Column(name = "commentDate", nullable = false)
// TODO : 지원하지 않는다고 하니 확인하기	
//	@Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)  // 작성하고 추가 할 수 없다.
	private UserEntity user;
	
	// BoardEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "boardId")  // 추가함
	private BoardEntity board;

}
