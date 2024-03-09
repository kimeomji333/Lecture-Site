package com.sparta.hh99springlv4.domain.lecture.controller;

import com.sparta.hh99springlv4.global.dto.ResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.LectureCategoryResponseDto;
import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LectureController {


    private final LectureService lectureService;

    // 강의 등록 + 권한자만 가능 @Secured 이용
    @Secured("ROLE_ADMIN")
    @PostMapping("/lecture")
    public ResponseDto<CreateLectureResponseDto> createLecture(@RequestBody CreateLectureRequestDto requestDto) {
        CreateLectureResponseDto responseDto = lectureService.createLecture(requestDto);
        return ResponseDto.success("강의 등록 성공", responseDto);
    }

    // 선택한 강의 조회  + 강사 정보 + 댓글들
    @GetMapping("/lecture/{lectureId}")
    public ResponseDto<GetLectureResponseDto> getLecture(@PathVariable Long lectureId) {
        GetLectureResponseDto responseDto = lectureService.getLecture(lectureId);
        return ResponseDto.success("강의 조회 성공", responseDto);
    }

    // 카테고리별 강의 목록 조회
    @GetMapping("/lecture/category/{category}")
    public ResponseDto<List<LectureCategoryResponseDto>> CategoryLecture(@PathVariable CategoryEnum category,
                                                                        @RequestParam(required = false, defaultValue = "lectureName") String orderBy,
                                                                        @RequestParam(required = false, defaultValue = "asc") String direction) {
        List<LectureCategoryResponseDto> responseDto = lectureService.CategoryLecture(category, orderBy, direction);
        return ResponseDto.success("카테고리별 강의 조회 성공", responseDto);
    }
}
