package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Data
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

	// 파일 업로드
	private String fileName;
	private String uuid;
	private String folderPath;

	public GalleryDTO(String fileName, String uuid, String folderPath) {
	}
}
