package com.sparta.hh99springlv4.domain.comment.dto;

import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String contents; //댓글내용
    private Long lectureId;
    private Long userId;

    // Entity -> Dto
    // 선택한 댓글 등록, 수정.
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.userId = comment.getUser().getId();
        this.lectureId = comment.getLecture().getId();
    }
}
