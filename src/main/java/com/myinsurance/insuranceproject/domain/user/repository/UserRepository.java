package com.myinsurance.insuranceproject.domain.user.repository;

import com.myinsurance.insuranceproject.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//  Optional<User> findByUsername(String username);
  Optional<User> findByEmail(String email); // 이메일 로그인
  Optional<User> findByPhoneNumber(String phoneNumber);

  boolean existsByPhoneNumber(String phoneNumber);

  Optional<User> findByKakaoId(String kakaoId);




}