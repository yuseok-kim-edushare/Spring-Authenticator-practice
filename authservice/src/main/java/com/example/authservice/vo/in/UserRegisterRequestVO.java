package com.example.authservice.vo.in;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestVO {
    private String loginID;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private String address;
    private LocalDate birthDate;
}
