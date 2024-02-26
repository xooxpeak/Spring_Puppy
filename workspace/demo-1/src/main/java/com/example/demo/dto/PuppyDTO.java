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
public class PuppyDTO {

	private Long id;
	
	@JsonProperty("puppy_name")
	private String puppyName;
	
	@JsonProperty("gender")
	private String gender;
	
	@JsonProperty("neutering")
	private boolean neutering;
	
	@JsonProperty("puppy_birth")
	private Date puppyBirth;
	
	@JsonProperty("breed")
	private String breed;
	
	@JsonProperty("allergy")
	private String allergy;
	
	@JsonProperty("personality")
	private String personality;
	
	@JsonProperty("introduction")
	private String introduction;
	
	@JsonProperty("profile_img")
	private String profileImg;
}
