package com.myinsurance.insuranceproject.domain.user.repository;

import com.myinsurance.insuranceproject.domain.user.entity.SmsVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsVerificationRepository extends JpaRepository<SmsVerification, String> {

    Optional<SmsVerification> findByPhoneNumber(String phoneNumber);

}
