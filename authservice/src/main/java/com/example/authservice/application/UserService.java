package com.example.authservice.application;

import com.example.authservice.dto.in.*;
import com.example.authservice.dto.out.*;
/*
 * 회원 관리 서비스
 * 1. 회원 가입
 * 2. 로그인
 * 3. 로그아웃
 * 4. 회원 정보 조회
 * 5. 회원 정보 수정
 * 6. 회원 탈퇴
 */

public interface UserService {
    // 회원 가입
    void registerUser(UserRegisterRequestDTO userRegisterRequestDTO);
    // 로그인
    UserLoginResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO);
}
