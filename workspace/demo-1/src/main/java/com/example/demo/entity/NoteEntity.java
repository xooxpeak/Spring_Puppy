package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "note_date")
	private Date noteDate;

	@Column(name = "meal", nullable = false)
	private String meal;

	@Column(name = "poop_frequency", nullable = false)
	private String poopFrequency;

	@Column(name = "poop_condition", nullable = false)
	private String poopCondition;
	
	@Column(name = "mood", nullable = false)
	private String mood;
	
	@Column(name = "daily", nullable = false)
	private String daily;
	
	// PuppyEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "puppy_id", insertable = false, updatable = false)
	private PuppyEntity puppy;
	
	// GalleryEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)   //CascadeType.All => 부모가 받는 속성을 자식도 그대로 물려받음
	@JoinColumn(name = "gallery_id")
	private List<GalleryEntity> galleryEntityList;
	
	// StoreEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "store_id", insertable = false, updatable = false)
	private StoreEntity entity;

}