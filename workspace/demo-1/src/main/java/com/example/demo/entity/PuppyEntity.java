package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity(name = "puppy")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuppyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name = "puppyId")
//	private String puppyId;
	
	@Column(name = "puppy_name", nullable = false)
	private String puppyName;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "neutering", nullable = false)
	private boolean neutering;   // TINYINT? boolean?
	
	@Column(name = "puppy_birth", nullable = false)
	private Date puppyBirth;
	
	@Column(name = "breed", nullable = false)
	private String breed;
	
	@Column(name = "allergy", nullable = false)
	private String allergy;
	
	@Column(name = "personality")
	private String personality;
	
	@Column(name = "introduction")
	private String introduction;
	
	@Column(name = "profile_img")
	private String profileImg;
	
	// NoteEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "note_id")
	private List<NoteEntity> noteEntityList;
	
	// StoreEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "store_id", insertable = false, updatable = false)
	private StoreEntity store;
		
	// UserEntity와 다대일 관계
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserEntity user;

}
