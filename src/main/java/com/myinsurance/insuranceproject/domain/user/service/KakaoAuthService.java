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

  public void signupByKakao(String kakaoEmail, String kakaoNickname) {
    // 중복 체크 (이메일 기준)
    if (userRepository.findByEmail(kakaoEmail).isPresent()) {
      throw new IllegalArgumentException("이미 가입된 카카오 계정입니다.");
    }

    // 카카오는 비밀번호가 없으므로 랜덤 생성
    String randomPassword = passwordEncoder.encode("kakao_" + kakaoEmail); // 또는 UUID 등

    User user = new User(
        kakaoNickname,
        randomPassword,
        kakaoEmail,
        "01000000000", // 실제 본인인증 이후에 받을 수 있음
        "KAKAO" //signtype
    );

    userRepository.save(user);
  }
}
