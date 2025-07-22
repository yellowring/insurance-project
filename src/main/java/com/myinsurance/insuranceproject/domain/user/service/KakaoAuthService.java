package com.myinsurance.insuranceproject.domain.user.service;

import com.myinsurance.insuranceproject.domain.user.entity.User;
import com.myinsurance.insuranceproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void signupByKakao(String kakaoId, String kakaoEmail, String kakaoNickname) {
    // 이메일 또는 카카오ID 중복 체크
    if (userRepository.findByKakaoId(kakaoId).isPresent()) {
      throw new IllegalArgumentException("이미 가입된 카카오 계정입니다.");
    }

    String randomPassword = passwordEncoder.encode("kakao_" + kakaoId);

    User user = User.builder()
            .username(kakaoNickname)
            .password(randomPassword)
            .email(kakaoEmail)
            .kakaoId(kakaoId)
            .signupType("KAKAO")
            .role("USER")
            .build();

    userRepository.save(user);
  }
}
