package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LikeListRepository;


@Service
public class LikeListService {
	
	@Autowired
	private LikeListRepository likeListRepository;
	
}
