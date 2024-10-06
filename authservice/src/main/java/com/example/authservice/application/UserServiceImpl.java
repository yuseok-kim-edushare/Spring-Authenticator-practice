package com.example.authservice.application;

import com.example.authservice.dto.in.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.authservice.infrastructure.UserRepository;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        userRegisterRequestDTO.encodePassword(passwordEncoder);
        userRepository.save(userRegisterRequestDTO.toEntity());
    }
}
