package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.StoreRepository;


@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
}
