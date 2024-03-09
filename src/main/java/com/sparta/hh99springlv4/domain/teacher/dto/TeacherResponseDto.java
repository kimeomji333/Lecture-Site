package com.sparta.hh99springlv4.domain.teacher.dto;

import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import lombok.Getter;


public class TeacherResponseDto {

    @Getter
    public static class CreateTeacherResponseDto {
        private final String teacherName; //강사이름
        private final Integer teacherCareer; //경력
        private final String teacherCompany; //회사
        private final String teacherPhone; //전화번호
        private final String teacherIntro; //소개

        public CreateTeacherResponseDto(Teacher teacher) {
            this.teacherName = teacher.getTeacherName();
            this.teacherCareer = teacher.getTeacherCareer();
            this.teacherCompany = teacher.getTeacherCompany();
            this.teacherPhone = teacher.getTeacherPhone();
            this.teacherIntro = teacher.getTeacherIntro();
        }
    }

    @Getter  // 선택한 강의 조회 시 강사 정보 확인 + 전화번호는 제외
    public static class GetTeacherResponseDto {
        private final String teacherName; //강사이름
        private final Integer teacherCareer; //경력
        private final String teacherCompany; //회사
        private final String teacherIntro; //소개

        public GetTeacherResponseDto(Teacher teacher) {
            this.teacherName = teacher.getTeacherName();
            this.teacherCareer = teacher.getTeacherCareer();
            this.teacherCompany = teacher.getTeacherCompany();
            this.teacherIntro = teacher.getTeacherIntro();
        }
    }
}


