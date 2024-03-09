package com.sparta.hh99springlv4.domain.teacher.controller;

import com.sparta.hh99springlv4.domain.teacher.dto.TeacherRequestDto.CreateTeacherRequestDto;
import com.sparta.hh99springlv4.domain.teacher.dto.TeacherResponseDto.CreateTeacherResponseDto;
import com.sparta.hh99springlv4.domain.teacher.service.TeacherService;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeacherController {

    private final TeacherService teacherService;

    // 강사 등록 + 권한자만 가능
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/teacher")
    public ResponseDto<CreateTeacherResponseDto> createTeacher(@RequestBody CreateTeacherRequestDto requestDto) {

        CreateTeacherResponseDto responseDto = teacherService.createTeacher(requestDto);

        return ResponseDto.success("강사 등록 성공", responseDto);
    }
}
