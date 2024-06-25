package com.example.demo.service;

import com.example.demo.config.SecurityUtil;
import com.example.demo.dto.PuppyDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.entity.PuppyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.PuppyMapper;
import com.example.demo.repository.PuppyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PuppyService {
	
	@Autowired
	private PuppyRepository puppyRepository;

    @Autowired
    private PuppyMapper puppyMapper;

    @Autowired
    private UserRepository userRepository;

    public ResDTO savePuppy(PuppyDTO puppyDTO) {

        // 현재 사용자의 ID를 가져옴
        String currentUserId = SecurityUtil.getCurrentUserId();

        // 로그인된 사용자가 없는 경우 예외 처리
        if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
            throw new RuntimeException("로그인한 사용자가 없습니다.");
        }

        // 현재 사용자의 UserEntity를 가져옴
        UserEntity currentUser = userRepository.findByUserId(currentUserId);

        // puppyDTO -> puppyEntity
        PuppyEntity puppy = puppyMapper.puppyDTOToPuppyEntity(puppyDTO);

        // puppyEntity에 현재 사용자 설정
        puppy.setUser(currentUser);

        return ResDTO.builder()
                .code("200")
                .message("강아지 등록 성공")
                .data(puppyRepository.save(puppy))
                .build();
    }
}
