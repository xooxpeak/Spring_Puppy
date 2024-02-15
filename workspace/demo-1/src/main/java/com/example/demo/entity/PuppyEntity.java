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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private List<NoteEntity> noteEntityList;
	
	// StoreEntity와 다대일 관계
	@ManyToOne   
	private StoreEntity store;
		
	// UserEntity와 다대일 관계
	@ManyToOne  
	private UserEntity user;

}
