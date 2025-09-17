package com.myinsurance.insuranceproject.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsVerification {

    @Id
    private String phoneNumber;

    private String code;

    private LocalDateTime expiresAt;

    private boolean verified; // 인증 여부

}
