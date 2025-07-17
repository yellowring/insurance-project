package com.myinsurance.insuranceproject.domain.user.controller;

import com.myinsurance.insuranceproject.domain.user.entity.User;
import com.myinsurance.insuranceproject.domain.user.dto.PhoneVerificationRequest;
import com.myinsurance.insuranceproject.domain.user.dto.SignupRequestDto;
import com.myinsurance.insuranceproject.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto) {
    userService.signup(requestDto);
    return ResponseEntity.ok("회원가입 성공");
  }

  @PostMapping("/verify-phone")
  public ResponseEntity<?> verifyPhone(@RequestBody @Valid PhoneVerificationRequest request) {
    String phoneNumber = request.getPhoneNumber();

    String code = String.format("%06d", new Random().nextInt(999999));
    LocalDateTime expiry = LocalDateTime.now().plusMinutes(3);

    Optional<User> optionalUser = userService.findUserByPhoneNumber(phoneNumber); // 💡 service 통해서 처리하게 변경
    User user;
    if (optionalUser.isPresent()) {
      user = optionalUser.get();
    } else {
      user = new User();
      user.setPhoneNumber(phoneNumber);
    }

    user.setVerificationCode(code);
    user.setVerificationCodeExpiry(expiry);
    userService.saveUser(user); // 💡 저장도 service 통해 위임

    log.info("[인증번호 전송] {} → {}", phoneNumber, code);
    return ResponseEntity.ok("인증번호 전송 완료");
  }

}
