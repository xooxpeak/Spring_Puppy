package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
	
	private Long id;
	
	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("board_date")
	private Date boardDate;
	
	@JsonProperty("views")
	private int views;
	
	@JsonProperty("user_like")
	private int userLike;

	private boolean isAuthor;  // 작성자인지 여부를 나타내는 필드 추가

	// 정적 팩토리 메서드
	public static BoardDTO of(BoardEntity boardEntity, boolean isAuthor) {
		return BoardDTO.builder()
				.id(boardEntity.getId())
				.userId(boardEntity.getUserId())
				.title(boardEntity.getTitle())
				.content(boardEntity.getContent())
				.boardDate(boardEntity.getBoardDate())
				.views(boardEntity.getViews())
				.userLike(boardEntity.getUserLike())
				.isAuthor(isAuthor)
				.build();
	}

}
