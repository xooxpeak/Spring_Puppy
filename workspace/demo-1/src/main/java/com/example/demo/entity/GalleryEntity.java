package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "gallery")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "galleryId", nullable = false)
	private Long galleryId;
	
	@Column(name = "noteId")
	private Long noteId;
	
	@Column(name = "gall_date", nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)   // 확인 필요
	private Date gallDate;
	
	// 서버에 이미지를 저장하고 -> 저장한 경로를 데이터베이스에 저장.
	// 실제로 이미지를 가져올 때 데이터베이스에 있는 이미지 경로를 찾아서 서버에서 찾아 이미지를 뿌려줌
	@Column(name = "gall_img")   // 경로
	private String gallImg;
	
	@Column(name = "gall_extension")   // 확장자
	private String gallExtension;
	
	// NoteEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private NoteEntity note;

}
