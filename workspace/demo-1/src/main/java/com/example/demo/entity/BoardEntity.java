package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

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

	@Column(name = "user_Id")
	private Long userId;   
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "board_date")
	private Date boardDate;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "user_like")
	private int userLike;
	
	// UserEntity와 다대일 관계 & 조인
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity user;
	
	// StoreEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "store_id", insertable = false, updatable = false)
	private StoreEntity store;
	
	// CommentEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private List<CommentEntity> commentEntityList;

	// LikeListEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "likeList_id")
	private List<LikeListEntity> likeListEntityList;
	
}
