package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDTO {

	private Long id;
	
	private String storeName;

	private String managerName;

	private String storePhone;
	
	private String storeAddr1;
	
	private String storeAddr2;
	
	private Long userId;
}
