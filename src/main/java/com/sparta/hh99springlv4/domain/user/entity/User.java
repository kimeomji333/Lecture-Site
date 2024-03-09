package com.sparta.hh99springlv4.domain.user.entity;

import com.sparta.hh99springlv4.domain.comment.entity.Comment;
import com.sparta.hh99springlv4.domain.like.entity.Like;
import com.sparta.hh99springlv4.domain.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userGender;  // 그렇군 enum 으로 GenderType gender 으로 할 수 있군.

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    // rphanRemoval = true 옵션 (JPA 에서 사용되는 옵션)
    // 일대다 관계에서 부모 엔티티(User)가 삭제될 때 자식 엔티티(Comment)도 함께 삭제
    // 자식 엔티티(Comment)의 생명주기를 관리할 수 있다.
    // 해당 컬렉션에 대해 고아(Orphan) 엔티티를 자동으로 제거
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    List<Like> likeList = new ArrayList<>();

    // 3) @Builder 를 사용하여 생성자를 자동으로 생성
    // 객체를 생성할 때 다양한 필드를 선택적으로 설정할 수 있
    // DTO 에서 값을 가져오는 과정을 거치지 않고도 다음과 같이 필드 값을 설정할 수 있다.
    @Builder
    public User(String userEmail, String userPassword, String userGender, String userPhone, String userAddress, UserRoleEnum role) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.role = role;
    }

    // 생성자 공부. Entity 클래스의 필드를 초기화하는 방법

    // 1) 외부에서 직접 값 전달받아 객체 생성
    // 주로 간단한 데이터 객체나 DTO와 같이 값이 고정된 객체를 생성할 때 사용
//    public User(Long id, String userEmail, String userPassword, String userGender, String userPhone, String userAddress,  UserRoleEnum role) {
//        this.id = id;
//        this.userEmail = userEmail;
//        this.userPassword = userPassword;
//        this.userGender = userGender;
//        this.userPhone = userPhone;
//        this.userAddress = userAddress;
//        this.role = role;
//    }

    // 2) dto 객체를 받아서 필드 값을 설정
    // 주로 클라이언트에서 전달된 요청을 처리하거나, 여러 필드를 가진 복잡한 객체를 생성할 때 사용
//    public User(UserRequestDto.SignupUserRequestDto requestDto) {
//        id는 안되네...
//        this.userEmail = requestDto.getUserEmail();
//        this.userPassword = requestDto.getUserPassword();
//        this.userGender = requestDto.getUserGender();
//        등등
//    }
}


