package com.myinsurance.insuranceproject.domain.user.controller;

import com.myinsurance.insuranceproject.domain.user.dto.SmsRequestDto;
import com.myinsurance.insuranceproject.domain.user.dto.SmsVerifyRequestDto;
import com.myinsurance.insuranceproject.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSms(@RequestBody SmsRequestDto request) {
        authService.sendSmsCode(request);
        return ResponseEntity.ok("인증번호가 전송되었습니다.");
    }

    @PostMapping("/verify-sms")
    public ResponseEntity<String> verifySms(@RequestBody SmsVerifyRequestDto request) {
        authService.verifySmsCode(request);
        return ResponseEntity.ok("인증 성공");
    }

    @GetMapping("/check-user")
    public ResponseEntity<Boolean> checkUser(@RequestParam String phone) {
        boolean exists = authService.checkUserExists(phone);
        return ResponseEntity.ok(exists);
    }


}
