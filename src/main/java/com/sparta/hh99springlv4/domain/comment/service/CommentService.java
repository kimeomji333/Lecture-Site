package com.sparta.hh99springlv4.domain.comment.service;

import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.domain.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.global.exception.NotFoundException;
import com.sparta.hh99springlv4.global.exception.UnauthorizedException;
import com.sparta.hh99springlv4.domain.user.repository.UserRepository;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final LectureRepository lectureRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    // 댓글 등록 기능
    public CommentResponseDto addCommentToLecture(Long lectureId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        // Lecture 객체. DB 에서 lectureId 로 강의 정보 조회
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new NotFoundException("해당 강의는 존재하지 않습니다: " + lectureId));

        // User 객체. DB 에서 userId 로 사용자 정보 조회
        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() ->
                new NotFoundException("해당 사용자를 찾을 수 없습니다: " + userDetails.getUser().getId()));

        //RequestDto -> comment Entity 생성
        Comment comment = new Comment(commentRequestDto);

        // Entity 연관관계 설절
        comment.setLecture(lecture);
        comment.setUser(user);

        // 댓글 Entity DB 에 저장
        Comment savedComment = commentRepository.save(comment);

        //  Entity -> Dto 로 변경하여 반환
        return new CommentResponseDto(savedComment);
    }


    // 선택한 강의의 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long lectureId, Long commentId, CommentRequestDto commentRequestDto, UserDetailsImpl userDetails) {

        // 1. 댓글 조회 Comment 객체
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("해당 댓글은 존재하지 않습니다."));

        // 2. 조회한 댓글이 선택한 강의의 댓글인지 확인
        // 서비스상 해당 댓글이 선택한 강의의 댓글인지 확인하는 건 덜 중요할 수도
        if (!comment.getLecture().getId().equals(lectureId)) {
            throw new NotFoundException("해당 강의의 댓글이 아닙니다.");
        }

        // 3. 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 수정할 수 있습니다.");
        }

        // 댓글 수정
        comment.updateContents(commentRequestDto);
        comment = commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    // 선택한 강의의 선택한 댓글 삭제
    public void deleteComment(Long commentId, UserDetailsImpl userDetails) {

        // DB 에서 commentId 로 댓글 정보 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("해당 댓글을 찾을 수 없습니다."));

        // 댓글을 작성한 사용자와 현재 로그인한 사용자가 일치하는지 확인
        if (!comment.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new UnauthorizedException("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }
        // 댓글 삭제
        commentRepository.delete(comment);
    }
}
