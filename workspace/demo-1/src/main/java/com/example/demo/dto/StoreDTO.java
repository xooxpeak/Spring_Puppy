package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO {

	private Long id;
	
	@JsonProperty("store_name")
	private String storeName;
	
	@JsonProperty("store_phone")
	private String storePhone;
	
	@JsonProperty("store_addr1")
	private String storeAddr1;
	
	@JsonProperty("store_addr2")
	private String storeAddr2;
	
	@JsonProperty("user_id")
	private Long user_id;
}
