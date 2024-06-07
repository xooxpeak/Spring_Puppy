package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/n")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 기능 : 댓글 작성
     * url : /saveComment
     * request data : 댓글 작성 아이디, 강아지 이름, 내용, 날짜, 시간
     * response data : 댓글 작성 성공
     */
    @PostMapping("/saveComment/{board_id}")
    public CommentDTO saveComment(@PathVariable("board_id") Long boardId, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(boardId, commentDTO);
    }




    /**
     기능 : 댓글 조회
     url : /comment/board?id={boardId}
     request data :
     response data :
     */
    @GetMapping("/comment/{board_id}")
    public List<CommentDTO> comment(@PathVariable("board_id") Long boardId) {
        return commentService.getCommentsByBoard(boardId);
    }


}
