package com.example.authservice.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import com.example.authservice.infrastructure.UserRepository;
import com.example.authservice.entity.User;
import com.example.authservice.vo.in.*;
import com.example.authservice.dto.in.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestVO userRegisterRequestVO) {
        UserRegisterRequestDTO userRegisterRequestDTO = UserRegisterRequestDTO.from(userRegisterRequestVO);

        return ResponseEntity.ok("User registered successfully");
    }
}
