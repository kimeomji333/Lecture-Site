package com.sparta.hh99springlv4.domain.lecture.entity;

import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import com.sparta.hh99springlv4.domain.teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "Lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false)
    private Long lecturePrice;

    @Column(nullable = false)
    private String lectureIntro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum lectureCategory;

    @Column(nullable = false)
    private LocalDate lectureRegistrationDate;

    @Column(name = "like_count")
    private long likeCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    //@OrderBy("id asc ") // 댓글 정렬
    private List<Comment> commentList = new ArrayList<>();

    // @Builder 어노테이션을 사용하여 빌더 패턴을 구현하는 메서드
    // 이 메서드는 객체를 생성할 때 사용되며, 각 필드의 값을 매개변수로 전달하여 객체 생성
    // 이때, 생성된 객체의 필드값이나 순서를 고려하지 않고도 객체를 생성할 수 있다.

    // 생성자 코드
    // @Builder 를 사용하여 생성자를 자동으로 생성하면
    // DTO 에서 값을 가져오는 과정을 거치지 않고도 다음과 같이 필드 값을 설정할 수 있다.
    @Builder
    public Lecture(String lectureName, Long lecturePrice, String lectureIntro, CategoryEnum lectureCategory, Teacher teacher) {
        this.lectureName = lectureName;
        this.lecturePrice = lecturePrice;
        this.lectureIntro = lectureIntro;
        this.lectureCategory = lectureCategory;
        this.lectureRegistrationDate = LocalDate.now();
        this.teacher = teacher;
    }

    // 연관관계 설정 LectureService.java 47줄과 내용 동일
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
}
