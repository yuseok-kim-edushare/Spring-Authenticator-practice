package com.example.authservice.dto.in;

import com.example.authservice.vo.in.UserRegisterRequestVO;
import com.example.authservice.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    private String loginID;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String address;
    private LocalDate birthDate;
    private String username;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public static UserRegisterRequestDTO from(UserRegisterRequestVO userRegisterRequestVO) {
        return new UserRegisterRequestDTO(
            userRegisterRequestVO.getLoginID(),
            userRegisterRequestVO.getPassword(),
            userRegisterRequestVO.getEmail(),
            userRegisterRequestVO.getPhoneNumber(),
            userRegisterRequestVO.getName(),
            userRegisterRequestVO.getAddress(),
            userRegisterRequestVO.getBirthDate(),
            UUID.randomUUID().toString()
        );
    }

    public User toEntity() {
        return User.builder()
            .loginID(loginID)
            .password(password)
            .email(email)
            .phoneNumber(phoneNumber)
            .name(name)
            .address(address)
            .birthDate(birthDate)
            .username(username)
            .build();
    }
}
