package com.example.demo.controller;

import com.example.demo.dto.ResDTO;
import com.example.demo.service.LikeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/y")
public class LikeListController {

    @Autowired
    private LikeListService likeListService;

    /**
     기능 : 좋아요
     url : /userLike
     request data :
     response data : 좋아요 성공
     */
    @PostMapping("/userLike/{boardId}")
    public ResDTO insertLike(@PathVariable("boardId") Long boardId, Authentication authentication) {
        String userId = authentication.getName(); // 인증된 사용자의 이름을 userId로 사용
        return likeListService.insertLike(boardId, userId);
    }
}
