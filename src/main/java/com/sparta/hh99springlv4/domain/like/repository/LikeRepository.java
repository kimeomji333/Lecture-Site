package com.sparta.hh99springlv4.domain.like.repository;

import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.like.entity.Like;
import com.sparta.hh99springlv4.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndLecture(User user, Lecture lecture);
    void deleteByUserAndLecture(User user, Lecture lecture);

    // 선택 강의 조회
    Long countByLecture(Lecture lecture);
}
