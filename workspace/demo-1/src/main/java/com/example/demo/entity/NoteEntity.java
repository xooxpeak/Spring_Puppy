package com.example.demo.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name = "condition", nullable = false)
	private String condition;
	
	@Column(name = "daily")
	private String daily;
	
	// PuppyEntity와 다대일 관계
	@ManyToOne   
	private PuppyEntity puppy;
	
	// GalleryEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)   //CascadeType.All => 부모가 받는 속성을 자식도 그대로 물려받음
	private List<GalleryEntity> galleryEntityList;
	
	// StoreEntity와 다대일 관계
	@ManyToOne
	private StoreEntity entity;

}
