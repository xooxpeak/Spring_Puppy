package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "store")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "store_name", nullable = false)
	private String storeName;

	@Column(name = "manager_name", nullable = false)
	private String managerName;

	@Column(name = "storePhone")
	private String storePhone;
	
	@Column(name = "store_addr1")
	private String storeAddr1;
	
	@Column(name = "store_addr2")
	private String storeAddr2;
	
	@Column(name = "user_id")
	private Long userId;
	
	// PuppyEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "puppy_id")
	private List<PuppyEntity> puppyEntityList;
	
	// NoteEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "note_id")
	private List<NoteEntity> noteEntityList;
	
	// BoardEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "board_id")
	private List<BoardEntity> boardEntityList;

	// TODO: UserEntity와 다대일 관계
//	@ManyToOne
//	@JoinColumn(name = "user_id", insertable = false, updatable = false)
//	@JsonBackReference
//	private UserEntity user;

}
