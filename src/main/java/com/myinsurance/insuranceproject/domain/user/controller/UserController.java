package com.myinsurance.insuranceproject.domain.user.controller;

import com.myinsurance.insuranceproject.domain.user.dto.SignupRequestDto;
import com.myinsurance.insuranceproject.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
