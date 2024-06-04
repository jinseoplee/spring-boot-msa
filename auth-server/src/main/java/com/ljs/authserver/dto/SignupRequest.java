package com.ljs.authserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입을 위한 DTO
 */
@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String confirmPassword;
}
