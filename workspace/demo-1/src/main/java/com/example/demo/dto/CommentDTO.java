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
public class CommentDTO {

	private Long id;
	
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("commen_date")
	private Date commentDate;
	
	@JsonProperty("comment")
	private String comment;
}
