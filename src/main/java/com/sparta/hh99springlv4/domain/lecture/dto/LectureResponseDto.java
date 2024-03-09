package com.sparta.hh99springlv4.domain.lecture.dto;

import com.sparta.hh99springlv4.domain.comment.dto.CommentResponseDto;
import com.sparta.hh99springlv4.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;


public class LectureResponseDto {

    // static 사용 이유
    // static 키워드는 해당 멤버(필드 또는 메서드)가 클래스 수준에 속하며,
    // 클래스의 모든 객체에 대해 단 하나의 인스턴스만 존재함을 의미
    // 이는 인스턴스가 아닌 클래스 자체에 연결되어 있음을 의미
    // DTO 에 정의된 static 필드는 클래스의 인스턴스를 생성하지 않고도 직접 접근할 수 있다.
    // 이는 객체를 생성하지 않고도 DTO 에 정의된 값을 사용할 수 있어 편리
    @Getter
    @AllArgsConstructor
    public static class CreateLectureResponseDto {

        // DTO 는 주로 데이터 전송을 위한 객체로 사용되며,
        // 한 번 생성되면 내부의 데이터가 변경되지 않는 것이 일반적
        // 보통 DTO 의 필드는 변경되지 않는 것으로 간주되어 final 키워드를 붙이는 것이 좋은 습관
        private final String lectureName;
        private final Long lecturePrice;
        private final String lectureIntro;
        private final CategoryEnum lectureCategory;
        private final Long teacherId;
        private final LocalDate lectureRegistrationDate;

        public CreateLectureResponseDto(Lecture lecture) {
            this.lectureName = lecture.getLectureName();
            this.lecturePrice = lecture.getLecturePrice();
            this.lectureIntro = lecture.getLectureIntro();
            this.lectureCategory = lecture.getLectureCategory();
            this.teacherId = lecture.getTeacher().getId();
            this.lectureRegistrationDate = lecture.getLectureRegistrationDate();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class GetLectureResponseDto {

        private final String lectureName;
        private final Long lecturePrice;
        private final String lectureIntro;
        private final CategoryEnum lectureCategory;
        private final LocalDate lectureRegistrationDate;

        // Entity 연관관계
        private final Long likes;
        // GetTeacherResponseDto 타입으로 teacher 필드 선언
        // teacher 필드를 통해 CreateLectureResponseDto 객체는 GetTeacherResponseDto 객체를 참조
        private final GetTeacherResponseDto teacher;
        private final List<CommentResponseDto> comments;

        public GetLectureResponseDto(Lecture lecture, Long likes) {
            this.lectureName = lecture.getLectureName();
            this.lecturePrice = lecture.getLecturePrice();
            this.lectureIntro = lecture.getLectureIntro();
            this.lectureCategory = lecture.getLectureCategory();
            this.lectureRegistrationDate = lecture.getLectureRegistrationDate();

            this.likes = likes;
            // 새로운 GetTeacherResponseDto 객체를 생성하여 teacher 필드에 할당
            this.teacher = new GetTeacherResponseDto(lecture.getTeacher());
            this.comments = lecture.getCommentList().stream().map(CommentResponseDto::new).toList();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class LectureCategoryResponseDto {

        private final String lectureName;
        private final Long lecturePrice;
        private final CategoryEnum lectureCategory;
        private final LocalDate lectureRegistrationDate;

        public LectureCategoryResponseDto(Lecture lecture) {
            this.lectureName = lecture.getLectureName();
            this.lecturePrice = lecture.getLecturePrice();
            this.lectureRegistrationDate = lecture.getLectureRegistrationDate();
            this.lectureCategory = lecture.getLectureCategory();
        }
    }
}