package com.ottAll.ottAll.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JwtSubject {

    private final String userId;

    private final String type;

    public static JwtSubject createAccessTokenSubject(String userId){
        return new JwtSubject(userId, "atk");
    }

    public static JwtSubject createRefreshTokenSubject(String userId){
        return new JwtSubject(userId, "rtk");
    }
}
