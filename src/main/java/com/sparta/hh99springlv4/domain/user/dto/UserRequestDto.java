package com.sparta.hh99springlv4.domain.user.dto;

import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class SignupUserRequestDto {

        @Email
        @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
        private String userEmail; // 이메일

        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
        private String userPassword;  // 비밀번호

        private String userGender;  // 성별
        private String userPhone;  // 전화번호
        private String userAddress;  // 주소
        private boolean admin = false;  // 권한
        private String adminToken = "";  // 권한토큰

        // RequestDto -> User Entity
        // 회원가입. 사용사 등록 메서드
        public User toEntity(String userPassword, UserRoleEnum role) {
            return User.builder()
                    .userEmail(this.userEmail)
                    .userPassword(userPassword)
                    .userGender(this.userGender)
                    .userPhone(this.userPhone)
                    .userAddress(this.userAddress)
                    .role(role)
                    .build();
        }
    }

    @Getter
    public static class LoginRequestDto {
        private String userEmail;
        private String userPassword;
    }
}
