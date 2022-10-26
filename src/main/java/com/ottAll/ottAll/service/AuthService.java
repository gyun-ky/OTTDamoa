package com.ottAll.ottAll.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ottAll.ottAll.dto.LoginRes;
import com.ottAll.ottAll.entity.Member;
import com.ottAll.ottAll.repository.MemberRepository;
import com.ottAll.ottAll.util.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 로그인
     * @param userId
     * @param password
     * @return
     * @throws JsonProcessingException
     */
    @Transactional(rollbackFor = Exception.class)
    public LoginRes login(String userId, String password) throws JsonProcessingException {

        try {

            Member member = memberRepository
                    .findByUserIdAndStatus(userId, Member.Status.ACTIVE)
                    .orElseThrow(() -> new NoSuchElementException("해당 사용자가 존재하지 않습니다."));

            if(!passwordEncoder.matches(password, member.getPassword())){
                throw new NoSuchElementException("비밀번호가 틀립니다.");
            }

            String accessToken = jwtUtil.createAccessToken(userId);
            String refreshToken = jwtUtil.createRefreshToken(userId);

            member.setRefreshToken(refreshToken);
            memberRepository.save(member);

            return new LoginRes(userId, accessToken, refreshToken);

        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 회원가입
     * @param userId 회원 아이디
     * @param password 회원 비밀번호
     */
    @Transactional(rollbackFor = Exception.class)
    public void signUp(String userId, String password){

        try {
            memberRepository.save(Member.builder()
                    .userId(userId)
                    .password(passwordEncoder.encode(password))
                    .status(Member.Status.ACTIVE)
                    .build());
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Access Token 재발급
     * @param accessToken
     * @param refreshToken
     * @return
     */
    public String reissueAccessToken(String accessToken, String refreshToken) throws Exception {

        try {

            if (!jwtUtil.tokenIsExpired(accessToken)) {
                throw new Exception("만료되지 않았거나 포맷이 맞지 않은 Access Token 입니다.");
            }

            String userId = jwtUtil.validateRefreshTokenAndGetUserId(refreshToken);

            Member member = memberRepository.findByUserIdAndRefreshTokenAndStatus(userId, refreshToken, Member.Status.ACTIVE)
                    .orElseThrow(() -> new NoSuchElementException("로그인되어 있지 않은 사용자입니다."));

            return jwtUtil.createAccessToken(userId);

        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new Exception("Refresh Token이 만료되었습니다. 다시 로그인해주세요.");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
