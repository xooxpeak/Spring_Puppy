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
public class GalleryDTO {

	private Long id;
	
	@JsonProperty("note_id")
	private Long noteId;
	
	@JsonProperty("gall_date")
	private Date gallDate;
	
	@JsonProperty("gall_img")
	private String gallImg;
	
	@JsonProperty("gall_extension")
	private String gallExtension;
	
}
