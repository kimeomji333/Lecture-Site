package com.sparta.hh99springlv4.domain.lecture.dto;

import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import lombok.Getter;


public class LectureRequestDto {

    @Getter
    public static class CreateLectureRequestDto {
        private String lectureName; //강의명
        private Long lecturePrice; //가격
        private String lectureIntro; //소개
        private CategoryEnum lectureCategory; //카테고리
        private Long teacherId ; //강사번호


        // toEntity 메서드 :  DTO -> Entity 객체 생성
        // 이 메서드는 주로 클라이언트에서 전달된 데이터 -> 엔티티 변환 -> 데이터베이스 저장
        // 이러한 변환 작업은 일반적으로 서비스 계층에서 이루어짐
        // 주로 생성자 대신에 사용되며, 빌더 패턴을 사용하여 엔티티를 생성

        // Teacher 객체를 매개변수로 받아서 해당 강의에 대한 강사 정보를 설정한 후, 엔티티 객체를 생성하여 반환
        // 주어진 DTO 에서 강사 정보를 가져와서 해당 강의에 대한 강사를 설정하는 역할
        // Lecture 엔티티의 teacher 필드에 해당하는 Teacher 객체를 설정

        // 강의 등록
        public Lecture toEntity(Teacher teacher) {
            return Lecture.builder()
                    .lectureName(this.lectureName)
                    .lecturePrice(this.lecturePrice)
                    .lectureIntro(this.lectureIntro)
                    .lectureCategory(this.lectureCategory)
                    .teacher(teacher)
                    .build();
        }
    }
}
