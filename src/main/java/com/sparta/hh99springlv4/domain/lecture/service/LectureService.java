package com.sparta.hh99springlv4.domain.lecture.service;

import com.sparta.hh99springlv4.domain.comment.repository.CommentRepository;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureRequestDto.CreateLectureRequestDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.CreateLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.GetLectureResponseDto;
import com.sparta.hh99springlv4.domain.lecture.dto.LectureResponseDto.LectureCategoryResponseDto;
import com.sparta.hh99springlv4.domain.lecture.entity.CategoryEnum;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.lecture.repository.LectureRepository;
import com.sparta.hh99springlv4.domain.like.repository.LikeRepository;
import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import com.sparta.hh99springlv4.domain.teacher.repository.TeacherRepository;
import com.sparta.hh99springlv4.global.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    // 또는 @AllArgsConstructor 사용
    public LectureService(LectureRepository lectureRepository, TeacherRepository teacherRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.lectureRepository = lectureRepository;
        this.teacherRepository = teacherRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    // 강의 등록 기능
    @Transactional
    public CreateLectureResponseDto createLecture(CreateLectureRequestDto requestDto) {

        // DB 에서 TeacherId 확인
        Teacher teacher = teacherRepository.findById(requestDto.getTeacherId()).orElseThrow(() ->
                new NotFoundException("해당 ID를 찾을 수 없습니다.")
        );

        // toEntity 메서드로 LectureResponseDto.java 에서 객체 생성하면서 Teacher 객체를 매개변수로 받아, 엔티티 객체 생성했으면,
        // 여기서는 연관관계 설정 필요없는지 궁금
        //lecture.setTeacher(teacher);

        // DB에 저장
        // toEntity 메서드 :  DTO -> Entity 객체 생성 파라미터로 담아서 바로 저장
        Lecture saveLecture = lectureRepository.save(requestDto.toEntity(teacher));

        // Entity -> ResponseDto 와 반환을 한번에
//        CreateLectureResponseDto lectureResponseDto = new CreateLectureResponseDto(saveLecture);
//        return lectureResponseDto;
        return new CreateLectureResponseDto(saveLecture);
    }

    // 선택한 강의 조회 기능
    @Transactional(readOnly = true) // DB 에서 데이터 변경하지 않으므로, 롤백될 가능성 적어진다.
    public GetLectureResponseDto getLecture(Long lectureId) {

        // DB 에서 강의 확인
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(() ->
                new NotFoundException("해당 강의를 찾을 수 없습니다.")
        );

        Long likes = likeRepository.countByLecture(lecture); // 수정 필요
        return new GetLectureResponseDto(lecture, likes);
    }

    // 카테고리별 강의 목록 조회 기능
    public List<LectureCategoryResponseDto> CategoryLecture(CategoryEnum category, String orderBy, String direction) {
        List<Lecture> lectureList = lectureRepository.findByLectureCategory(category);

        // 유효성 검사: orderBy 값이 올바른지 확인
        if (!Arrays.asList("lectureName", "lecturePrice", "registrationDate").contains(orderBy)) {
            throw new IllegalArgumentException("Invalid orderBy value: " + orderBy);
        }

        // 유효성 검사: direction 값이 올바른지 확인
        if (!Arrays.asList("asc", "desc").contains(direction)) {
            throw new IllegalArgumentException("Invalid direction value: " + direction);
        }

        // 조회된 결과가 비어 있는 경우
        if (lectureList.isEmpty()) {
            throw new NotFoundException("No lectures found for category: " + category);
        }

        switch (orderBy) {
            case "lectureName":
                lectureList.sort((l1, l2) -> direction.equals("asc") ? l1.getLectureName().compareTo(l2.getLectureName()) : l2.getLectureName().compareTo(l1.getLectureName()));
                break;
            case "lecturePrice":
                lectureList.sort((l1, l2) -> direction.equals("asc") ? l1.getLecturePrice().compareTo(l2.getLecturePrice()) : l2.getLecturePrice().compareTo(l1.getLecturePrice()));
                break;
            case "registrationDate":
                lectureList.sort((l1, l2) -> direction.equals("asc") ? l1.getLectureRegistrationDate().compareTo(l2.getLectureRegistrationDate()) : l2.getLectureRegistrationDate().compareTo(l1.getLectureRegistrationDate()));
                break;
            default:
                // 기본적으로 강의명으로 내림차순 정렬
                lectureList.sort((l1, l2) -> l2.getLectureName().compareTo(l1.getLectureName()));
        }

        List<LectureCategoryResponseDto> responseList = new ArrayList<>();
        for (int i = 0; i < lectureList.size(); i++) {
            responseList.add(new LectureCategoryResponseDto(lectureList.get(i)));
        }
        return responseList;
    }
}
// stream 먼저 이해하고, for 문으로 바꾸기
// 위와 같은 코드
//        return lectureList.stream()
//                .map(LectureCategoryResponseDto::new)
//                .toList());


