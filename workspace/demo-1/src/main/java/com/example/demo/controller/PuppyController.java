package com.example.demo.controller;

import com.example.demo.dto.PuppyDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.service.PuppyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/y")
public class PuppyController {

	@Autowired
	PuppyService puppyService;

	/**
	 기능 : 강아지 등록
	 url : /savePuppy
	 request data : 이름, 성별, 중성화여부, 생년월일, 종, 알러지 유무, 성격, 자기소개
	 response data : 강아지 등록 성공
	 */
	@PostMapping("/savePuppy")
	public ResDTO savePuppy(@RequestBody PuppyDTO puppyDTO) {
		return puppyService.savePuppy(puppyDTO);
	}


	/**
	 기능 : 강아지 조회
	 url : /puppy
	 request data :
	 response data : 강아지 조회 성공
	 */
	@GetMapping("/puppy")
	public List<PuppyDTO> puppy() {
		return puppyService.puppy();
		}


	/**
	 기능 : 강아지 정보 수정 위한 상세 정보 조회
	 url : /getPuppy
	 request data :
	 response data : 강아지 상세 정보
	 */
	@GetMapping("/getPuppy")
	public PuppyDTO getPuppy(@RequestParam(name = "id", required = false) Long id) {
		// 특정 강아지 상세 정보 조회 및 반환
		return puppyService.getPuppy(id);
	}

	/**
	 기능 : 강아지 정보 수정
	 url : /editPuppy
	 request data :
	 response data : 강아지 정보 수정 성공
	 */
	@PostMapping("/editPuppy")
	public PuppyDTO editPuppy(@RequestParam(name = "id") Long id, @RequestBody PuppyDTO puppyDTO) {
		return puppyService.editPuppy(id, puppyDTO);
	}


	/**
	 기능 : 강아지 정보 삭제
	 url : /deletePuppy
	 request data : puppy_id
	 response data : 강아지 삭제 성공
	 */
	@DeleteMapping("/deletePuppy")
	public void deletePuppy(@RequestParam(name = "id") Long id) {
		puppyService.deletePuppy(id);
	}

	}



