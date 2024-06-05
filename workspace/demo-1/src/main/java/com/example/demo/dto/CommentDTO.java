package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

	private Long id;
	
	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("board_id")
	private Long boardId;
	
	@JsonProperty("comment_date")
	private Date commentDate;
//	private String commentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

	@JsonProperty("comment")
	private String comment;
}
