package com.sparta.hh99springlv4.domain.like.entity;

import com.sparta.hh99springlv4.domain.lecture.entity.Lecture;
import com.sparta.hh99springlv4.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean likes = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    // 생성자는 해당 엔티티의 인스턴스를 생성할 대 사용.
    // user 와 lecture 를 받아서 그에 해당하는 like 엔티티 생성
    @Builder
    public Like(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
    }
}
