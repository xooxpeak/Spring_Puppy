package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/n")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 기능 : 댓글 작성
     * url : /createComment
     * request data : 댓글 작성 아이디, 강아지 이름, 내용, 날짜, 시간
     * response data : 댓글 작성 성공
     */
    @PostMapping("/saveComment/{id}")
    public Long saveComment(@PathVariable("id") Long id, @RequestParam(name = "user_id") Long userId, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(id, userId, commentDTO);
    }


    /**
     기능 : 댓글 조회
     url : /comment/board?id={id}
     request data :
     response data :
     */
    @GetMapping("/comment/{id}")
    public List<CommentDTO> comment(@PathVariable("id") Long id) {
        return commentService.getCommentsByBoard(id);
    }


}
