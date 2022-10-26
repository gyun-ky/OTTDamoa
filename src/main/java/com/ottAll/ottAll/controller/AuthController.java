package com.ottAll.ottAll.controller;

import com.ottAll.ottAll.config.BaseResponse;
import com.ottAll.ottAll.dto.*;
import com.ottAll.ottAll.service.AuthService;
import com.ottAll.ottAll.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/web/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * [POST] 로그인
     * @param loginReq
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody LoginReq loginReq){

        try{

            LoginRes loginRes = authService.login(loginReq.getUserId(), loginReq.getPassword());

            return new ResponseEntity<>(new BaseResponse<LoginRes>(loginRes), HttpStatus.OK);

        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * [POST] 회원가입
     * @param signUpReq
     * @return
     */
    @ResponseBody
    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse> signUp(@RequestBody SignUpReq signUpReq){

        try{

            authService.signUp(signUpReq.getUserId(), signUpReq.getPassword());

            return new ResponseEntity<>(new BaseResponse(HttpStatus.OK), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * [POST] Access Token 재발급
     * @param tokenReissueReq
     * @return
     */
    @ResponseBody
    @PostMapping("/token-reissue")
    public ResponseEntity<BaseResponse> tokenReissue(@RequestBody TokenReissueReq tokenReissueReq){

        try{

            String newAccessToken = authService.reissueAccessToken(tokenReissueReq.getAccessToken(), tokenReissueReq.getRefreshToken());
            TokenReissueRes tokenReissueRes = new TokenReissueRes(newAccessToken);

            return new ResponseEntity<>(new BaseResponse(tokenReissueRes), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.UNAUTHORIZED, e), HttpStatus.UNAUTHORIZED);
        }
    }
}
