package com.example.authservice.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.example.authservice.vo.in.*;
import com.example.authservice.dto.in.*;
import com.example.authservice.application.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestVO userRegisterRequestVO) {
        userService.registerUser(UserRegisterRequestDTO.from(userRegisterRequestVO));
        return ResponseEntity.ok("회원가입 성공");
    }
}
