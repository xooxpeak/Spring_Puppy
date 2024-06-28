package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "puppy")
@Table(name = "puppy")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PuppyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "puppy_name")
	private String puppyName;
	
	@Column(name = "gender")
	private String gender;  // 'M' 또는 'F' 값으로 설정
	
	@Column(name = "neutering")
	private String neutering;
	
	@Column(name = "puppy_birth")
	private String puppyBirth;
	
	@Column(name = "breed")
	private String breed;
	
	@Column(name = "allergy")
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
