package com.myinsurance.insuranceproject.domain.user.service;

import com.myinsurance.insuranceproject.domain.user.entity.User;
import com.myinsurance.insuranceproject.domain.user.repository.UserRepository;
import com.myinsurance.insuranceproject.domain.user.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void signup(SignupRequestDto requestDto) {

    // 이메일 중복 체크
    if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
      throw new IllegalArgumentException("이미 등록된 이메일입니다.");
    }

    String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

    User user = new User(
        requestDto.getUsername(),
        encodedPassword,
        requestDto.getEmail(),
        requestDto.getPhoneNumber(),
        "EMAIL" // signType : 카카오는 kakao로 다른 서비스에 따로받음.
    );

    userRepository.save(user);
  }

  public Optional<User> findUserByPhoneNumber(String phoneNumber) {
    return userRepository.findByPhoneNumber(phoneNumber);
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

}
