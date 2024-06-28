package com.example.demo.dto;

import com.example.demo.entity.PuppyEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PuppyDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("user_id")
	private Long userId; // 사용자 ID

	@JsonProperty("puppy_name")
	private String puppyName;
	
	@JsonProperty("gender")
	private String gender;  // 'M' 또는 'F' 값으로 설정
	
	@JsonProperty("neutering")
	private String neutering;
	
	@JsonProperty("puppy_birth")
	private String puppyBirth;
	
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

	public PuppyDTO(PuppyEntity puppy) {
	}
}
