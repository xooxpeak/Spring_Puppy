package com.example.demo.service;

import com.example.demo.dto.GalleryDTO;
import com.example.demo.entity.GalleryEntity;
import com.example.demo.mapper.GalleryMapper;
import com.example.demo.repository.GalleryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Log4j2
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    // 업로드 할 위치
    @Value("${part.upload.path}")
    private String uploadPath;
    private String fileExtension;
    private Path savePath;

    // 사진첩 작성
    public List<GalleryDTO> createGallery(List<MultipartFile> uploadFiles) {
        List<GalleryDTO> galleryDTOList = new ArrayList<>();

        // 업로드 할 위치
//		String uploadPath = "C:/Users/xooxpeak/Desktop/과외/Upload";

        // Path savePath=null;
        //String fileExtension=null;

        for (MultipartFile uploadFile : uploadFiles) {
            // 이미지 파일만 업로드
            // TODO: 확장자를 빼오는 방법으로 수정
            if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
                log.warn("this file is not an image type");
                continue;

                // 파일 확장자를 추출
                //		String originalName = uploadFile.getOriginalFilename();
                //		String fileExtension = originalName != null ? StringUtils.getFilenameExtension(originalName) : null;

                //		// 이미지 파일인지 확인
                //		if (fileExtension == null || !MediaType.IMAGE_JPEG_VALUE.equals(fileExtension) &&
                //				!MediaType.IMAGE_PNG_VALUE.equals(fileExtension) &&
                //				!MediaType.IMAGE_GIF_VALUE.equals(fileExtension)) {
                //			log.warn("Uploaded file is not an image: {}", originalName);
                //			continue;
                //		}
            }

            // 파일명: 업로드 된 파일의 원래 파일 이름을 추출
            String originalName = uploadFile.getOriginalFilename();

            // 마지막으로 온 "/"부분으로부터 +1 해준 부분부터 출력
            // originalName이 "/path/to/file/example.txt"와 같은 문자열이라면, "example.txt" 부분만 (파일명만) 추출
            String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
            log.info("fileName: " + fileName);

            // 파일 확장자
            fileExtension = StringUtils.getFilenameExtension(originalName);

            // 날짜 폴더 생성
            String folderPath = makeFolder();

            // UUID
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_"를 이용하여 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            // Paths.get() 메서드는 특정 경로의 파일 정보를 가져옴 (경로 정의하기)
            savePath = Paths.get(saveName);

            try {
                // transferTo(file) : uploadFile에 파일을 업로드 하는 메서드
                uploadFile.transferTo(savePath);
                galleryDTOList.add(new GalleryDTO(fileName, uuid, folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GalleryEntity galleryEntity = GalleryEntity.builder()
                .gallDate(Date.valueOf(LocalDate.now()))
                .gallImg(String.valueOf(savePath))
                .gallExtension(fileExtension)
                .build();

        galleryRepository.save(galleryEntity);

        return galleryDTOList;
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


// 사진첩 목록 조회

// Mapstruct 사용 x
//	public List<GalleryDTO> galleryList() {
//		List<GalleryEntity> galleryEntities = galleryRepository.findAll();
//		List<GalleryDTO> galleryDTOList = new ArrayList<>();
//
//		for (GalleryEntity entity : galleryEntities) {
//			GalleryDTO dto = new GalleryDTO();
//
//			dto.setGallImg(entity.getGallImg());
//			dto.setGallExtension(entity.getGallExtension());
//
//			galleryDTOList.add(dto);
//		}
//
//		return galleryDTOList;
//	}

    // mapstruct 사용
    public List<GalleryDTO> galleryList() {
        List<GalleryEntity> galleryEntities = galleryRepository.findAll();   // 갤러리엔터티를 리스트에 저장
        return galleryEntities.stream()  // 스트림 사용
                .map(GalleryMapper.instance::galleryToDTO)  // Mapstruct 사용하여 갤러리 entity -> DTO로 변환
                .collect(Collectors.toList());  //  변환된 갤러리 DTO들을 리스트로 수집하여 반환
    }

    // 해당 유저의 사진첩 상세조회
    public GalleryEntity findById(Long id) {
        return galleryRepository.findById(id).get();

    }




    // 사진첩 수정
//    public List<GalleryDTO> updateGallery(Long id, GalleryUpdateDTO) {
//    //    GalleryEntity entityToDelete = GalleryMapper.instance.galleryToDTO(updateDTO);
//        Gallery gallery = galleryRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 게시물이 없습니다 "+id));
//        gallery.update(dto.getGallImg(), dto.getExtension());
//    }



    // 사진첩 삭제
    public void delete(Long id) {
        GalleryEntity galleryEntity = galleryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + id));
        galleryRepository.deleteById(galleryEntity.getId());
    }
}
