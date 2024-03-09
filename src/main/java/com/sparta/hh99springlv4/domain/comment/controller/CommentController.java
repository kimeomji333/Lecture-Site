package com.sparta.hh99springlv4.domain.comment.controller;

import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.domain.comment.service.CommentService;
import com.sparta.hh99springlv4.domain.lecture.service.LectureService;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

// 댓글은 리팩토링 안하고 남겨둠. 비교
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    // 선택한 강의의 댓글 등록
    @PostMapping("/lectures/{lectureId}/comments")
    public CommentResponseDto addCommentToLecture(@PathVariable Long lectureId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.addCommentToLecture(lectureId, commentRequestDto, userDetails);
    }


    // 선택한 강의의 댓글 수정
    @PutMapping("/lectures/{lectureId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long lectureId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(lectureId, commentId, commentRequestDto, userDetails);
    }

    // 선택한 강의의 선택한 댓글 삭제
    // ResponseEntity 를 사용하면,
    // Spring Framework 에서 제공하는 클래스로, HTTP 응답을 표현하고 커스터마이징하는 데 사용
    // 주로 컨트롤러에서 처리된 결과를 클라이언트에 반환할 때 사용
    @DeleteMapping("/lectures/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
