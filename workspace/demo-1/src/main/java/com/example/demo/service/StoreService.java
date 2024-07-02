package com.example.demo.service;

import com.example.demo.config.SecurityUtil;
import com.example.demo.dto.ResDTO;
import com.example.demo.dto.StoreDTO;
import com.example.demo.entity.StoreEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.StoreMapper;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private UserRepository userRepository;


	/**
	 기능 : 유치원 등록
	 */
	public ResDTO saveStore(StoreDTO storeDTO) {

		// 현재 사용자의 ID를 가져옴
		String currentUserId = SecurityUtil.getCurrentUserId();

		// 로그인된 사용자가 없는 경우 예외 처리
		if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
			throw new RuntimeException("로그인한 사용자가 없습니다.");
		}

		// 현재 사용자의 UserEntity를 가져옴
		UserEntity currentUser = userRepository.findByUserId(currentUserId);

		// storeDTO -> storeEntity
		StoreEntity store = storeMapper.storeToEntity(storeDTO);

		// storeEntity에 현재 사용자 설정
		store.setUserId(currentUser.getId());

		return ResDTO.builder()
				.code("200")
				.message("유치원 등록 성공")
				.data(storeRepository.save(store))
				.build();

	}
	
}
