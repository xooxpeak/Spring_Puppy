package com.example.demo.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "board")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Column(name = "boardId")
//	private Long boardId;

//	@Column(name = "userId")
//	private Long userId;   
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "boardDate")
	private Date boardDate;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "userLike")
	private int userLike;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserEntity user;
	
	// CommentEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	private List<CommentEntity> commentEntityList;

	// LikeListEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	private List<LikeListEntity> likeListEntityList;
	
}
