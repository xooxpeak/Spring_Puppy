package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

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
	
	@JsonProperty("mood")
	private String condition;
	
	@JsonProperty("daily")
	private String daily;

}
