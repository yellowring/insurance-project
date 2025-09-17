package com.myinsurance.insuranceproject.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400: 잘못된 요청(입력값 오류 등)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // 401: 인증 실패(로그인 필요)
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<String> handleUnauthorized(Exception ex) {
        return ResponseEntity.status(401).body("인증이 필요합니다.");
    }

    // 403: 권한 없음(인가 실패)
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<String> handleForbidden(Exception ex) {
        return ResponseEntity.status(403).body("접근 권한이 없습니다.");
    }

    // 404: 찾을 수 없음(엔티티 없음 등)
    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(jakarta.persistence.EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    // 409: 충돌/중복(이미 있음)
    @ExceptionHandler(org.springframework.dao.DuplicateKeyException.class)
    public ResponseEntity<String> handleConflict(org.springframework.dao.DuplicateKeyException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    // 500: 서버에러 (예상 못 한 오류)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleServerError(Exception ex) {
        return ResponseEntity.status(500).body("서버 내부 오류가 발생했습니다.");
    }
}
