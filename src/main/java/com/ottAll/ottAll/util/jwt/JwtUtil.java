package com.ottAll.ottAll.util.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.repository.MemberRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("jwt.secret")
    private String secretKey;

    private final ObjectMapper objectMapper;

    private final MemberRepository memberRepository;

    private final long accessExpireTime = 60 * 60 * 1000L; // 1시간
    // private final long accessExpireTime = 1 * 60 * 1000L * 60 * 24 * 500; // 500일

    private final long refreshExpireTime = 1 * 60 * 1000L * 60 * 24 * 14; // 14일

    /**
     * Access token 생성
     * @param userId
     * @return
     * @throws JsonProcessingException
     */
    public String createAccessToken(String userId) throws JsonProcessingException {
        JwtSubject jwtSubject = JwtSubject.createAccessTokenSubject(userId);
        String jwtSubjectStr = objectMapper.writeValueAsString(jwtSubject);

        Claims claims = Jwts.claims()
                .setSubject(jwtSubjectStr);

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Refresh Token 생성
     * @param
     * @return
     * @throws JsonProcessingException
     */
    public String createRefreshToken(String userId) throws JsonProcessingException {
        JwtSubject jwtSubject = JwtSubject.createRefreshTokenSubject(userId);
        String jwtSubjectStr = objectMapper.writeValueAsString(jwtSubject);

        Claims claims = Jwts.claims()
                .setSubject(jwtSubjectStr);

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + refreshExpireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Header에서 Jwt token 추출
     * @return
     */
    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    /**
     * access token Validatino 및 userID 추출
     * @param accessToken
     * @return
     * @throws Exception
     */
    public String validateAccessTokenAndGetUserId(String accessToken) throws Exception{

        try{
            String subjectStr = (String) Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getSubject();

            JwtSubject jwtSubject = objectMapper.readValue(subjectStr, JwtSubject.class);

            if(jwtSubject.getType().equals("atk")){
                return jwtSubject.getUserId();
            }else{
                throw new Exception("token의 type이 access Token이 아닙니다.");
            }

        } catch (ExpiredJwtException e) {
            System.out.println("<<<<<<ATK Expired - need to Reissue>>>>>>");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("<<<<<<ATK, RTK Expired - need to Login>>>>>>");
            throw e;
        }
    }

    /**
     * refresh token Validatino 및 userID 추출
     * @param refreshToken
     * @return
     * @throws Exception
     */
    public String validateRefreshTokenAndGetUserId(String refreshToken) throws Exception{

        try{
            String subjectStr = (String) Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(refreshToken)
                    .getBody()
                    .getSubject();

            JwtSubject jwtSubject = objectMapper.readValue(subjectStr, JwtSubject.class);

            if(jwtSubject.getType().equals("rtk")){
                return jwtSubject.getUserId();
            }else{
                throw new Exception("token의 type이 access Token이 아닙니다.");
            }

        } catch (ExpiredJwtException e) {
            System.out.println("<<<<<<RTK Expired - need to Reissue>>>>>>");
            throw e;
        } catch (Exception e) {
            System.out.println("<<<<<<RTK Expired - need to Login>>>>>>");
            throw e;
        }
    }

    public boolean tokenIsExpired(String token){

        try{

            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return false;
        } catch (ExpiredJwtException e) {
            System.out.println("<<<<<<TOKEN Expired>>>>>>");
            return true;
        } catch (Exception e) {
            System.out.println("<<<<<<TOKEN format is not valid>>>>>>");
            return false;
        }
    }

}
