package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public String test(@RequestParam(value="name") String name) {  // 받는 파라미터의 변수 이름과 리액트에서 요청 시 보내는 key의 이름이 동일
		 System.out.println(name);  // 출력 확인
		return name;
	}
	
	@GetMapping("/test2/t123")
	public String test2(@RequestParam(value="name") String name) {  // 받는 파라미터의 변수 이름과 리액트에서 요청 시 보내는 key의 이름이 동일
		 System.out.println(name);  // 출력 확인
		return name;
	}
}
