package com.example.authservice.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserLoginResponseVO {

    private String accessToken;
    private String refreshToken;
    private String name;
}
