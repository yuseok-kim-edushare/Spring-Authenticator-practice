package com.example.authservice.application;

import com.example.authservice.dto.in.*;
import com.example.authservice.dto.out.*;
import com.example.authservice.entity.User;
import com.example.authservice.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.authservice.infrastructure.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        userRegisterRequestDTO.encodePassword(passwordEncoder);
        userRepository.save(userRegisterRequestDTO.toEntity());
    }

    @Override
    public UserLoginResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO) {
        User user = userRepository.findByLoginID(userLoginRequestDTO.getLoginID())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(userLoginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String accessToken = jwtTokenProvider.issueJwt(user.getUsername(), false);
        String refreshToken = jwtTokenProvider.issueRefresh(accessToken);
        return UserLoginResponseDTO.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
