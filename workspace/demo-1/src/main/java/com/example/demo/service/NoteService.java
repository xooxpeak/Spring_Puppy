package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.NoteRepository;


@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
}
