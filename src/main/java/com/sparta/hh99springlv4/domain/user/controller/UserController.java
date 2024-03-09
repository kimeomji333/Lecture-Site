package com.sparta.hh99springlv4.domain.user.controller;

import com.sparta.hh99springlv4.global.dto.ResponseDto;
import com.sparta.hh99springlv4.domain.user.dto.UserRequestDto.SignupUserRequestDto;
import com.sparta.hh99springlv4.domain.user.dto.UserResponseDto.SignupResponseDto;
import com.sparta.hh99springlv4.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/user/signup")
    public ResponseDto<SignupResponseDto> signup(@Valid @RequestBody SignupUserRequestDto requestDto, BindingResult bindingResult) {

        // Validation 예외처리
        // 유효성 검사에서 발생한 예외를 처리하고, 클라이언트에게 적절한 응답을 제공하는 역할
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseDto.fail("회원가입 요청이 잘못되었습니다.");
        }
        SignupResponseDto responseDto = userService.signup(requestDto);
        return ResponseDto.success("회원가입을 성공했습니다.", responseDto);
    }

    // 수환님이 만드신 회원 탈퇴 기능 (진짜 대단해!)
    //     @DeleteMapping
    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    @Operation(summary = "회원 탈퇴 기능", description = "회원 탈퇴할 수 있는 API")
    //    public ResponseDto<Void> delete(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    //        memberService.delete(userDetails.getUsername());
    //        return ResponseDto.success("회원 탈퇴 기능", null);
    //    }
}