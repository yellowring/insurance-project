package com.myinsurance.insuranceproject.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SmsVerifyRequestDto {
    private String phoneNumber;
    private String code;
}
