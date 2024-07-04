package com.example.demo.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDTO {
	
	private Long id;
	
	private Date noteDate;
	
	private String meal;
	
	private String poopFrequency;

	private String poopCondition;
	
	private String mood;
	
	private String daily;

}