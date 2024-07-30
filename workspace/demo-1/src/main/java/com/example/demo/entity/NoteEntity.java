package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class NoteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "note_date", nullable = false)
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

	// PuppyId를 직접 저장하는 컬럼 추가
	@Column(name = "puppy_id", nullable = false)
	private Long puppyId;
	
	// PuppyEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "puppy_id", insertable = false, updatable = false)
	@JsonBackReference
	private PuppyEntity puppy;
	
	// StoreEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "store_id", insertable = false, updatable = false)
	private StoreEntity store;

}