package com.myinsurance.insuranceproject.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;


  @Getter
  @NoArgsConstructor
  public class SignupRequestDto {

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수 입력 사항입니다.")
    @Pattern(regexp = "^[0-9]+$", message = "전화번호는 숫자만 입력해야 합니다.")
    private String phoneNumber;

    // signupType은 서버에서 처리 (UserService)
  }

