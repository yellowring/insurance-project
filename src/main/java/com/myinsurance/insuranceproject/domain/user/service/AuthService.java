package com.myinsurance.insuranceproject.domain.user.service;

import com.myinsurance.insuranceproject.domain.user.dto.SmsRequestDto;
import com.myinsurance.insuranceproject.domain.user.dto.SmsVerifyRequestDto;
import com.myinsurance.insuranceproject.domain.user.entity.SmsVerification;
import com.myinsurance.insuranceproject.domain.user.repository.SmsVerificationRepository;
import com.myinsurance.insuranceproject.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SmsVerificationRepository smsRepo;
    private final UserRepository userRepository;


    public void sendSmsCode(SmsRequestDto request) {
        String code = generateCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(3);

        SmsVerification sms = SmsVerification.builder()
                .phoneNumber(request.getPhoneNumber())
                .code(code)
                .expiresAt(expiresAt)
                .build();

        smsRepo.save(sms);

        // 전송 로직 생략 → 콘솔 출력(임시)
        System.out.println("[SMS] " + request.getPhoneNumber() + " → 인증번호: " + code);
    }

    public void verifySmsCode(SmsVerifyRequestDto request) {
        SmsVerification sms = smsRepo.findById(request.getPhoneNumber())
                .orElseThrow(() -> new RuntimeException("인증 요청이 존재하지 않습니다."));

        if (!sms.getCode().equals(request.getCode())) {
            throw new RuntimeException("인증번호가 일치하지 않습니다.");
        }

        if (sms.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("인증번호가 만료되었습니다.");
        }

        // 인증 성공 시
        sms.setVerified(true);
        smsRepo.save(sms);

        // 여기까지 오면 인증 성공
        System.out.println("인증 성공: " + request.getPhoneNumber());
    }

    public boolean checkUserExists(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }


    private String generateCode() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }
}
