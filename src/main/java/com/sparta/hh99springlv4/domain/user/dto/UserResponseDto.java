package com.sparta.hh99springlv4.domain.user.dto;

import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import lombok.Getter;

public class UserResponseDto {

    // 또는 @AllArgsConstructor 사용하기
    @Getter
    public static class SignupResponseDto {

        private final String userEmail; //이메일
        private final String userPassword; // 비밀번호
        private final String userGender; // 성별
        private final String userPhone; //전화번호
        private final String userAddress; // 주소
        private final UserRoleEnum role;  //권한

        public SignupResponseDto(User user) {
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userGender = user.getUserGender();
            this.userPhone = user.getUserPhone();
            this.userAddress = user.getUserAddress();
            this.role = user.getRole();
        }
    }
}
