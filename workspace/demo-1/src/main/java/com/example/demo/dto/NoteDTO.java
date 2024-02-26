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
public class NoteDTO {
	
	private Long id;
	
	@JsonProperty("note_date")
	private Date noteDate;
	
	@JsonProperty("meal")
	private String meal;
	
	@JsonProperty("poop")
	private String poop;
	
	@JsonProperty("condition")
	private String condition;
	
	@JsonProperty("daily")
	private String daily;

}
