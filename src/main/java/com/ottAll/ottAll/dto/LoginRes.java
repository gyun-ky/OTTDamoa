package com.ottAll.ottAll.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRes {

    private String userId;
    private String accessToken;
    private String refreshToken;

}
