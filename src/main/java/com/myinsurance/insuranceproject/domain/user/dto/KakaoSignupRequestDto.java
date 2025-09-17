package com.myinsurance.insuranceproject.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoSignupRequestDto {

    @NotBlank(message = "카카오 토큰 필수")
    private String kakaoAccessToken; // 카카오 인증 토큰

    @NotBlank(message = "카카오ID 필수")
    private String kakaoId;          // 카카오 고유 ID

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;            // 이메일

    @NotBlank(message = "휴대폰 번호는 필수 입력 사항입니다.")
    private String phoneNumber;
}
