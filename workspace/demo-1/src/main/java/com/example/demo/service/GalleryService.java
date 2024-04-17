package com.example.demo.service;

import com.example.demo.dto.UploadResDTO;
import com.example.demo.repository.GalleryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
@Log4j2
public class GalleryService {
	
	@Autowired
	private GalleryRepository galleryRepository;

	// 업로드 할 위치
	@Value("${part4.upload.path}")
	private String uploadPath;

	public List<UploadResDTO> createGallery(MultipartFile[] uploadFiles) {
		List<UploadResDTO> uploadResDTOList = new ArrayList<>();

		// 업로드 할 위치
//		String uploadPath = "C:/Users/xooxpeak/Desktop/과외/Upload";

		for (MultipartFile uploadFile : uploadFiles) {
			// 이미지 파일만 업로드
			if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
				log.warn("this file is not an image type");
				continue;
			}

			// 파일명: 모든 경로를 포함한 파일 이름
			String originalName = uploadFile.getOriginalFilename();
			// 원본 파일 이름이 null이 아닌지 확인
			assert originalName != null;
			// 마지막으로 온 "/"부분으로부터 +1 해준 부분부터 출력
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
			log.info("fileName: " + fileName);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 "_"를 이용하여 구분
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

			// Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
			Path savePath = Paths.get(saveName);

			try {
				// transferTo(file) : uploadFile에 파일을 업로드 하는 메서드
				uploadFile.transferTo(savePath);
				uploadResDTOList.add(new UploadResDTO(fileName, uuid, folderPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return uploadResDTOList;
	}

	// 날짜 폴더 생성
	public String makeFolder() {
		//LocalDate를 문자열로 포멧
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("/", File.separator);

		// make folder --------
		// File newFile= new File(dir,"파일명");
		// -> 부모 디렉토리를 파라미터로 인스턴스 생성
		File uploadPathFolder = new File(uploadPath, folderPath);

		// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미
		// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
		// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		if(!uploadPathFolder.exists()) {
			boolean mkdirs = uploadPathFolder.mkdirs();
			log.info("-------------------makeFolder------------------");
			log.info("uploadPathFolder.exists(): "+uploadPathFolder.exists());
			log.info("mkdirs: "+mkdirs);
		}

		return folderPath;

	}
	
}
