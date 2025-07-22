package com.myinsurance.insuranceproject.domain.user.controller;

import com.myinsurance.insuranceproject.domain.user.dto.KakaoSignupRequestDto;
import com.myinsurance.insuranceproject.domain.user.entity.User;
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

  @PostMapping("/signup/email")
  public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto) {
    userService.signupEmail(requestDto);
    return ResponseEntity.ok("이메일 회원가입 성공");
  }

  @PostMapping("/signup/kakao")
  public ResponseEntity<String> signup(@RequestBody @Valid KakaoSignupRequestDto requestDto) {
    userService.signupKakao(requestDto);
    return ResponseEntity.ok("카카오 회원가입 성공");
  }




}
