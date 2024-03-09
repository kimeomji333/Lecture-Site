package com.sparta.hh99springlv4.domain.like.controller;

import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.like.dto.LikeRequestDto;
import com.sparta.hh99springlv4.domain.like.dto.LikeResponseDto;
import com.sparta.hh99springlv4.domain.like.service.LikeService;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.service.UserService;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
    }

    // 선택한 강의 종아요
    @PostMapping("/lectures/{lectureId}/likes")
    public ResponseDto<String> addLike(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        likeService.addLike(lectureId, userDetails);

        return ResponseDto.success("요청 성공적", "success");
    }
}
