package com.example.authservice.dto.in;

import com.example.authservice.vo.in.UserLoginRequestVO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequestDTO {

    private String loginID;
    private String password;

    public static UserLoginRequestDTO from(UserLoginRequestVO userLoginRequestVO) {
        return new UserLoginRequestDTO(userLoginRequestVO.getLoginID(), userLoginRequestVO.getPassword());
    }
}
