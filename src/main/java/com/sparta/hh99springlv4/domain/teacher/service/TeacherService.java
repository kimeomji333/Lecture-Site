package com.sparta.hh99springlv4.domain.teacher.service;

import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.sparta.hh99springlv4.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import com.sparta.hh99springlv4.domain.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    // 강사 등록 기능
    public CreateTeacherResponseDto createTeacher(CreateTeacherRequestDto requestDto) {
        // DB에 저장
        Teacher teacher = teacherRepository.save(requestDto.toEntity());
        return new CreateTeacherResponseDto(teacher);
    }
}

// 또는 하나씩
// // RequestDto -> Entity
//        Teacher teacher = new Teacher(teacherRequestDto);
//
//        // DB에 저장
//        Teacher saveTeacher = teacherRepository.save(teacher);
//
//        // Entity -> ResponseDto
//        TeacherResponseDto teacherResponseDto = new TeacherResponseDto(saveTeacher);
//
//        return teacherResponseDto;
