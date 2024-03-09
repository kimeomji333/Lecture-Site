package com.sparta.hh99springlv4.domain.teacher.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
//@JsonIgnoreProperties({"teacher_name", "career", "company", "phone", "introduction"})
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String teacherName;

    @Column(nullable = false)
    private Integer teacherCareer;

    @Column(nullable = false)
    private String teacherCompany;

    @Column(nullable = false, unique = true)
    private String teacherPhone;

    @Column(nullable = false)
    private String teacherIntro;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Lecture> lectureList = new ArrayList<>();

    @Builder
    public Teacher(String teacherName, Integer teacherCareer, String teacherCompany, String teacherPhone, String teacherIntro) {
        this.teacherName = teacherName;
        this.teacherCareer = teacherCareer;
        this.teacherCompany = teacherCompany;
        this.teacherPhone = teacherPhone;
        this.teacherIntro = teacherIntro;
    }
}

//    public Teacher(TeacherRequestDto teacherRequestDto) {
//        this.teacherName = teacherRequestDto.getTeacherName();
//        this.teacherCareer = teacherRequestDto.getTeacherCareer();
//        this.teacherCompany = teacherRequestDto.getTeacherCompany();
//        this.teacherPhone = teacherRequestDto.getTeacherPhone();
//        this.teacherIntro = teacherRequestDto.getTeacherIntro();
//    }

