package com.ottAll.ottAll.util.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtSubject {

    private String userId;

    private String type;

    public static JwtSubject createAccessTokenSubject(String userId){
        return new JwtSubject(userId, "atk");
    }

    public static JwtSubject createRefreshTokenSubject(String userId){
        return new JwtSubject(userId, "rtk");
    }
}
