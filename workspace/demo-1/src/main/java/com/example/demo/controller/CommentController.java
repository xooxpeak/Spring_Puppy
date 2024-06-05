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
     * url : /createComment
     * request data : 댓글 작성 아이디, 강아지 이름, 내용, 날짜, 시간
     * response data : 댓글 작성 성공
     */
//    @PostMapping("/saveComment/{board_id}")
//    public Long saveComment(@PathVariable("board_id") Long boardId,  @RequestParam(name = "user_id", required = false) Long userId, @RequestBody CommentDTO commentDTO) {
//        System.out.println("Received request to save comment");
//        System.out.println("Board ID: " + boardId);
//        System.out.println("User ID: " + userId);
//        System.out.println("Comment: " + commentDTO.getComment());
//        return commentService.saveComment(boardId, userId, commentDTO);
//    }

//    @PostMapping("/saveComment/{board_id}")
//    public ResponseEntity<Long> saveComment(
//            @PathVariable("board_id") Long boardId,
//            @RequestBody CommentDTO commentDTO
//    ) {
//        // 현재 사용자의 ID를 가져옴
//        Long currentUserId = SecurityUtil.getCurrentUserIdAsLong();
//
//        // 로그인된 사용자가 없는 경우 예외 처리
//        if (currentUserId == null || "anonymousUser".equals(currentUserId)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        // 댓글 저장 요청 처리
//        Long commentId = commentService.saveComment(boardId, currentUserId, commentDTO);
//
//        return ResponseEntity.ok(commentId);
//    }

    @PostMapping("/saveComment/{board_id}")
    public CommentDTO saveComment(@PathVariable("board_id") Long boardId, @RequestBody CommentDTO commentDTO) {
        return commentService.saveComment(boardId, commentDTO);
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
