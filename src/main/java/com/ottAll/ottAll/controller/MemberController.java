package com.ottAll.ottAll.controller;

import com.ottAll.ottAll.config.BaseResponse;
import com.ottAll.ottAll.dto.LoginReq;
import com.ottAll.ottAll.dto.LoginRes;
import com.ottAll.ottAll.dto.RetrieveProfileRes;
import com.ottAll.ottAll.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/web/member")
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/profile")
    public RetrieveProfileRes retrieveProfile(){
        System.out.println("---retrievePofile---");
        return new RetrieveProfileRes("hello");
    }
}
