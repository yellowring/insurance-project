package com.myinsurance.insuranceproject.domain.user.service;

import com.myinsurance.insuranceproject.domain.user.dto.KakaoSignupRequestDto;
import com.myinsurance.insuranceproject.domain.user.dto.SignupRequestDto;
import com.myinsurance.insuranceproject.domain.user.entity.User;
import com.myinsurance.insuranceproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // 이메일 회원가입
  public void signupEmail(SignupRequestDto requestDto) {
    if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
      throw new IllegalArgumentException("이미 등록된 이메일입니다.");
    }

    String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

    User user = User.builder()
            .username(requestDto.getUsername())
            .password(encodedPassword)
            .email(requestDto.getEmail())
            .phoneNumber(requestDto.getPhoneNumber()) // SMS 인증 완료 번호
            .signupType("EMAIL")
            .kakaoId(null)
            .build();

    userRepository.save(user);
  }

  // 카카오 회원가입
  public void signupKakao(KakaoSignupRequestDto requestDto) {
    if (userRepository.findByKakaoId(requestDto.getKakaoId()).isPresent()) {
      throw new IllegalArgumentException("이미 가입된 카카오 계정입니다.");
    }

    User user = User.builder()
            .username(requestDto.getEmail())  // 닉네임 없으면 이메일로 대체 가능
            .password(null)                   // 카카오 회원은 비밀번호 없음
            .email(requestDto.getEmail())
            .phoneNumber(requestDto.getPhoneNumber())  // SMS 인증 완료 번호
            .signupType("KAKAO")
            .kakaoId(requestDto.getKakaoId())
            .build();

    userRepository.save(user);
  }
}
