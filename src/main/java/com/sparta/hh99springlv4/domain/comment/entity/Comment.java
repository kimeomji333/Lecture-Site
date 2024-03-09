package com.sparta.hh99springlv4.domain.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hh99springlv4.domain.comment.dto.CommentRequestDto;
import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //댓글내용
    private String contents;

    // Lecture 엔티티에 대한 참조를 반환하는 메서드
    // Comment 엔티티(주인)에서 Lecture 엔티티에 대해 참조해야함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @JsonIgnore
    private Lecture lecture;

    // 댓글을 작성한 사용자 정보를 반환하는 메서드
    // 여러개의 댓글이 하나의 사용자에게만 속함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    // CommentService. setUser, setLecture 메서드는
    // Comment 엔티티의 user 필드에 User 엔티티를 설정하는 역할
    // Comment 엔티티의 lecture 필드에 Lecture 엔티티를 설정하는 역할
    public void setUser(User user) {
        this.user = user;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    // 댓글 등록 생성자
    public Comment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }

    // 댓글 수정 메서드
    public void updateContents(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
    }
}
