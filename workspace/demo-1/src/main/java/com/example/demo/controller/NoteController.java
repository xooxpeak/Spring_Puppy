package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/y")
public class NoteController {

	@Autowired
	NoteService noteService;

	/**
	  기능 : 알림장 작성  ( 훈련사 계정 )
	  url : /saveNote
	  request data : 날짜, 식사 및 간식(아침·점심·저녁·간식), 배변 상태(횟수·상태), 기분 및 컨디션, 오늘 하루
	  response data : 알림장 작성 성공
	 */
	@PostMapping("/saveNote")
	public ResDTO saveNote(@RequestBody NoteDTO noteDTO) {
		return noteService.saveNote(noteDTO);
	}


	/**
	 기능 : 알림장 목록
	 url : /note
	 request data :
	 response data : 알림장 목록
	 */
	@GetMapping("/note")
	public List<NoteDTO> note() {
		return noteService.note();
	}


	/**
	 기능 : 알림장 상세보기
	 url : /noteDetail
	 request data : 특정 날짜의 알림장 상세보기
	 response data : 해당 날짜, 식사 및 간식(아침·점심·저녁·간식), 배변 상태(횟수·상태), 기분 및 컨디션, 오늘 하루
	 */
	@GetMapping("/noteDetail/{id}")
	public NoteDTO noteDetail(@PathVariable Long id) {
		return noteService.noteDetail(id);
	}


	/**
	  기능 : 알림장 수정 ( 훈련사 계정 )
	  url : /updateNote
	  request data : 특정 날짜의 식사 및 간식(아침·점심·저녁·간식), 배변 상태(횟수·상태), 기분 및 컨디션, 오늘 하루
	  response data : 알림장 수정 성공
	 */

	@PutMapping("/updateNote")
	public Object updateNote() {
		return null;
	}


	/**
	  기능 : 알림장 삭제 ( 훈련사 계정 )
	  url : /deleteNote
	  request data : 특정 날짜의 알림장 삭제
	  response data : 알림장 삭제 성공
	 */

	@DeleteMapping("/deleteNote")
	public Object deleteNote() {
		return null;
	}



	
	
}
