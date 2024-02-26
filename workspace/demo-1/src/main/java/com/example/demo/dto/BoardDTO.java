package com.example.demo.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
