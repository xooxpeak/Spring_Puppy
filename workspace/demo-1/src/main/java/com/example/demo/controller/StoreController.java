package com.example.demo.controller;

import com.example.demo.dto.ResDTO;
import com.example.demo.dto.StoreDTO;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/y")
public class StoreController {

    @Autowired
    StoreService storeService;

    /**
     기능 : 유치원 등록
     url : /saveStore
     request data : 유치원 이름, 관리자 이름, 유치원 전화번호, 주소, 상세주소
     response data : 유치원 등록 성공
     */
    @PostMapping("/saveStore")
    public ResDTO saveStore(@RequestBody StoreDTO storeDTO) {
        return storeService.saveStore(storeDTO);
    }


    /**
     기능 : 유치원 목록 조회
     url : /storeList
     request data :
     response data :
     */


    /**
     기능 : 유치원 상세 조회
     url : /store
     request data :
     response data :
     */


    /**
     기능 : 유치원 정보 수정
     url : /editStore
     request data : 유치원 이름, 관리자 이름, 유치원 전화번호, 주소, 상세주소
     response data :
     */


    /**
     기능 : 유치원 삭제
     url : /deleteStore
     request data :
     response data :
     */


}
