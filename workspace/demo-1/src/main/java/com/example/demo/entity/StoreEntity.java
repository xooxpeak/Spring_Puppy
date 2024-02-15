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
	
	@Column(name = "store_name", nullable = false)
	private String storeName;
	
	@Column(name = "store_phone")
	private String storePhone;
	
	@Column(name = "store_addr1")
	private String storeAddr1;
	
	@Column(name = "store_addr2")
	private String storeAddr2;
	
	@Column(name = "user_id")
	private Long user_id;
	
	// PuppyEntity와 일대다 관계
	@OneToMany(cascade = CascadeType.ALL)
	private List<PuppyEntity> puppyEntityList;
	

}
