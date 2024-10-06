package com.example.authservice.vo.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserLoginRequestVO {

    private String loginID;
    private String password;
}
