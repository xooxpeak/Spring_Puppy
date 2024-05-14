package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity(name = "note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "noteId")
//	private Long noteId;
	
	@Column(name = "note_date", nullable = false)
	private Date noteDate;   
	
	@Column(name = "meal", nullable = false)
	private String meal;
	
	@Column(name = "poop", nullable = false)
	private String poop;
	
	@Column(name = "mood", nullable = false)
	private String condition;
	
	@Column(name = "daily")
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
