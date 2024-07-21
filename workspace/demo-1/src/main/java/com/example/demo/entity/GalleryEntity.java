package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity(name = "gallery")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GalleryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "gall_date", nullable = false)
	private Date gallDate;
	
	@Column(name = "gall_img")
	private String gallImg;

	@Column(name = "gall_extension")   // 확장자
	private String gallExtension;


	// PuppyEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "puppy_id", insertable = false, updatable = false)
	private PuppyEntity puppy;

	}