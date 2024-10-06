package com.example.authservice.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import com.example.authservice.vo.out.UserLoginResponseVO;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class UserLoginResponseDTO {

    private String accessToken;
    private String refreshToken;
    private String name;

    public UserLoginResponseVO toVO() {
        return UserLoginResponseVO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .name(name)
                .build();
    }
}
