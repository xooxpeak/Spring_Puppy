package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "gallery")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	@JsonBackReference
	private PuppyEntity puppy;

	}