package com.example.demo.controller;

import com.example.demo.dto.PuppyDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.service.PuppyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
