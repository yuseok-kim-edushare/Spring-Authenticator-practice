package com.example.authservice.dto.in;

import com.example.authservice.vo.in.UserRegisterRequestVO;
import com.example.authservice.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String roles;

    public static UserRegisterRequestDTO from(UserRegisterRequestVO userRegisterRequestVO) {
        return new UserRegisterRequestDTO(
            userRegisterRequestVO.getLoginID(),
            userRegisterRequestVO.getPassword(),
            userRegisterRequestVO.getEmail(),
            userRegisterRequestVO.getPhoneNumber(),
            userRegisterRequestVO.getName(),
            userRegisterRequestVO.getAddress(),
            userRegisterRequestVO.getBirthDate(),
            "ROLE_USER"
        );
    }
}
