package com.sparta.hh99springlv4.domain.like.service;

import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.like.dto.LikeResponseDto;
import com.sparta.hh99springlv4.domain.like.entity.Like;
import com.sparta.hh99springlv4.domain.like.repository.LikeRepository;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.repository.UserRepository;
import com.sparta.hh99springlv4.global.exception.NotFoundException;
import com.sparta.hh99springlv4.global.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, LectureRepository lectureRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository;
    }

    // 선택한 강의 좋아요
    @Transactional
    public void addLike(Long lectureId, UserDetailsImpl userDetails) {

        User user = userRepository.findById(userDetails.getUser().getId()).orElseThrow(() ->
                new NotFoundException("선택한 회원을 찾을 수 없습니다.")
        );

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new NotFoundException("선택한 강의를 찾을 수 없습니다.")
        );

        // 좋아요 여부 확인
        boolean alreadyLiked = likeRepository.existsByUserAndLecture(user, lecture);

        if (!alreadyLiked) {

            lecture.incrementLikeCount();
            Like like = new Like(user, lecture); // true 처리

            // BD 저장
            likeRepository.save(like);
        }
        else {
            lecture.decrementLikeCount();
            // 음수가 나올 수 있으니 수정
//            lecture.setLikeCount(lecture.getLikeCount() -1);
            likeRepository.deleteByUserAndLecture(user, lecture);
        }
        // 수정해야 할 부분
        // 좋아요를 두번 누를 경우, DB 의 행이 삭제 되어, Likes 열이 매번 true 만 나옴
    }
}
