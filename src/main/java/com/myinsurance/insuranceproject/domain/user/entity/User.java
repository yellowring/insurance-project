package com.myinsurance.insuranceproject.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true) // 본인인증 후 업데이트
    private String phoneNumber;

    @Column(nullable = false)
    private String signupType; // EMAIL or KAKAO

    private String role = "USER";

    private LocalDateTime createAt = LocalDateTime.now();

    // 간편비밀번호 관련 필드 추가
    @Column(nullable = true)
    private String simplePassword; // 간편비밀번호 (6자리 숫자)

    @Column(nullable = false)
    private boolean simplePasswordSet = false; // 간편비밀번호 설정 여부

    @Column(nullable = false)
    private int simplePasswordFailCount = 0; // 간편비밀번호 실패 횟수

    @Column(nullable = true)
    private LocalDateTime simplePasswordLockedUntil; // 간편비밀번호 잠금 해제 시간

    // 본인인증 관련 필드 추가
    @Column(nullable = false)
    private boolean phoneVerified = false; // 휴대폰 본인인증 완료 여부

    @Column(nullable = true)
    private String verificationCode; // 인증 코드 (임시 저장)

    @Column(nullable = true)
    private LocalDateTime verificationCodeExpiry; // 인증 코드 만료 시간


    public User(String username, String password, String email, String phoneNumber, String signupType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.signupType = signupType;
        this.role = "USER";
        this.createAt = LocalDateTime.now();
    }

    // 간편비밀번호 설정 (비밀번호 설정 시 실패 횟수 초기화 및 잠금 해제)
    public void setSimplePassword(String encodedSimplePassword) {
        this.simplePassword = encodedSimplePassword;
        this.simplePasswordSet = true;
        this.simplePasswordFailCount = 0;
        this.simplePasswordLockedUntil = null;
    }

    // 간편비밀번호 실패 횟수 증가 (5회 이상 실패 시 30분간 잠금)
    public void incrementSimplePasswordFailCount() {
        this.simplePasswordFailCount++;
        if (this.simplePasswordFailCount >= 5) {
            this.simplePasswordLockedUntil = LocalDateTime.now().plusMinutes(30);
        }
    }

    // 간편비밀번호 실패 횟수 및 잠금 상태 초기화 (성공 시 호출)
    public void resetSimplePasswordFailCount() {
        this.simplePasswordFailCount = 0;
        this.simplePasswordLockedUntil = null;
    }

    // 간편비밀번호 잠금 여부 확인 -> 현재 시간이 잠금 해제 시간보다 전이면 (아직 잠금 중)
    public boolean isSimplePasswordLocked() {
        return this.simplePasswordLockedUntil != null &&
                LocalDateTime.now().isBefore(this.simplePasswordLockedUntil);
    }

    // 휴대폰 본인인증 처리 (전화번호 저장 + 인증 완료 처리)
    public void verifyPhone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.phoneVerified = true;
    }

}

