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

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PuppyService {

    @Autowired
    private PuppyRepository puppyRepository;

    @Autowired
    private PuppyMapper puppyMapper;

    @Autowired
    private UserRepository userRepository;

    /**
     기능 : 강아지 등록
     */
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
        puppy.setUserId(currentUser.getId());

        return ResDTO.builder()
                .code("200")
                .message("강아지 등록 성공")
                .data(puppyRepository.save(puppy))
                .build();
    }


    /**
     기능 : 강아지 조회
     */
    public List<PuppyDTO> puppy() {
        // 현재 사용자의 ID를 가져옴
        String currentUserId = SecurityUtil.getCurrentUserId();

        // 로그인된 사용자가 없는 경우 예외 처리
        if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
            throw new RuntimeException("로그인한 사용자가 없습니다.");
        }

        // 현재 사용자의 UserEntity를 가져옴
        UserEntity currentUser = userRepository.findByUserId(currentUserId);

        // 사용자의 강아지 목록 조회
        List<PuppyEntity> puppies = puppyRepository.findByUserId(currentUser.getId());

        return puppies.stream()
                .map(puppyMapper::puppyEntityToPuppyDTO)
                .collect(Collectors.toList());

    }


    /**
     기능 : 특정 강아지 정보 수정 위한 상세 정보 조회
     */
    public PuppyDTO getPuppy(Long id) {
        PuppyEntity puppy = puppyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 강아지 정보를 찾을 수 없습니다."));
        PuppyDTO puppyDTO = puppyMapper.puppyEntityToPuppyDTO(puppy);
        return puppyDTO;
    }


    /**
     기능 : 강아지 정보 수정
     */
    public PuppyDTO editPuppy(Long id, PuppyDTO puppyDTO) {
        // 강아지 찾기
        PuppyEntity puppy = puppyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));

        // 수정 (업데이트)
        puppy.setPuppyName(puppyDTO.getPuppyName());
        puppy.setPuppyBirth(puppyDTO.getPuppyBirth());
        puppy.setBreed(puppyDTO.getBreed());
        puppy.setGender(puppyDTO.getGender());
        puppy.setNeutering(puppyDTO.getNeutering());
        puppy.setAllergy(puppyDTO.getAllergy());
        puppy.setPersonality(puppyDTO.getPersonality());
        puppy.setIntroduction(puppyDTO.getIntroduction());

        // 수정된 정보 저장
        puppyRepository.save(puppy);

        return puppyMapper.puppyEntityToPuppyDTO(puppy);
    }


    /**
     기능 : 강아지 정보 삭제
     */
    public void deletePuppy(Long id) {
        PuppyEntity puppyEntity = puppyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid puppy ID: " + id));
        puppyRepository.delete(puppyEntity);
    }

}