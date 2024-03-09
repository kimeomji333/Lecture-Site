package com.sparta.hh99springlv4.domain.user.service;

import com.sparta.hh99springlv4.domain.user.dto.UserResponseDto.SignupResponseDto;
import com.sparta.hh99springlv4.domain.user.dto.UserRequestDto.SignupUserRequestDto;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원 가입
    @Transactional
    public SignupResponseDto signup(SignupUserRequestDto requestDto) {
        String userEmail = requestDto.getUserEmail();
        String userPassword = passwordEncoder.encode(requestDto.getUserPassword());  // 평문을 암호화 해서 password 에 담음.

        // email 중복확인
        Optional<User> checkEmail = userRepository.findByUserEmail(userEmail);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인 (권한확인)
        UserRoleEnum role = UserRoleEnum.USER;

        // signupRequestDto.equals(auth) 대체 검토 (매니저 외 권한 확장 가능성 고려)
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = userRepository.save(requestDto.toEntity(userPassword, role));
        return new SignupResponseDto(user);
    }

    // 수환님이 만드신 회원 탈퇴 기능 (진짜 대단해!)
//    @Transactional
//    public void delete(String email) {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
//                new CustomApiException(ErrorCode.MEMBER_ACCOUNT_NOT_FOUND.getMessage())
//        );
//
//        memberRepository.delete(member);
//    }

}
