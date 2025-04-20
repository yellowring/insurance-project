package com.myinsurance.insuranceproject.security;

import com.myinsurance.insuranceproject.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

  private final User user;

  public CustomUserDetails(User user) {
    this.user = user;
  }

  // 사용자 권한 반환
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList(); // TODO 권한 수정 시 빈리스트 대신 권한목록 넘기기
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  // 계정 만료 여부 (true면 사용 가능)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // 계정 잠김 여부
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 자격 증명(비밀번호) 만료 여부
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정 활성화 여부
  @Override
  public boolean isEnabled() {
    return true;
  }

  public User getUser() {
    return this.user;
  }
}
