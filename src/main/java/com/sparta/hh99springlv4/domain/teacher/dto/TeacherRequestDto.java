package com.sparta.hh99springlv4.domain.teacher.dto;

import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import lombok.Getter;


public class TeacherRequestDto {

    @Getter
    public static class CreateTeacherRequestDto {
        private String teacherName; //강사이름
        private int teacherCareer; //경력
        private String teacherCompany; //회사
        private String teacherPhone; //전화번호
        private String teacherIntro; //소개

        public Teacher toEntity() {
            return Teacher.builder()
                    .teacherName(this.teacherName)
                    .teacherCareer(this.teacherCareer)
                    .teacherCompany(this.teacherCompany)
                    .teacherPhone(this.teacherPhone)
                    .teacherIntro(this.teacherIntro)
                    .build();
        }
    }
}
