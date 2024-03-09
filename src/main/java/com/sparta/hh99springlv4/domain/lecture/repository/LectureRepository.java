package com.sparta.hh99springlv4.domain.lecture.repository;

import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByLectureCategory(CategoryEnum category);
}


// teacher에서 사용중.
// Lecture에서 강사와 일치하는 정보들을 내림차순으로 정렬하는 쿼리문
//    @Query("SELECT l FROM Lecture l WHERE l.teacher.id = :teacherId ORDER BY l.registrationDate DESC")
//    List<Lecture> findLecturesByTeacherIdOrderByRegistrationDateDesc(Long teacherId);

