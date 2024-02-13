package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "store")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Column(name = "storeId")
//	private Long storeId;
	
	@Column(name = "storeName", nullable = false)
	private String storeName;
	
	@Column(name = "storePhone")
	private String storePhone;
	
	@Column(name = "storeAddr1")
	private String storeAddr1;
	
	@Column(name = "storeAddr2")
	private String storeAddr2;
	
	@Column(name = "userId")
	private Long userId;
	
	// PuppyEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	private List<PuppyEntity> puppyEntityList;
	

}
