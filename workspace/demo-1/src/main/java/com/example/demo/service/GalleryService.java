package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.GalleryRepository;


@Service
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository;
	
}
