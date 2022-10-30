package com.ottAll.ottAll.controller;

import com.ottAll.ottAll.config.BaseResponse;
import com.ottAll.ottAll.dto.LoginReq;
import com.ottAll.ottAll.dto.LoginRes;
import com.ottAll.ottAll.dto.RetrievePollDto;
import com.ottAll.ottAll.dto.RetrieveProfileRes;
import com.ottAll.ottAll.service.MemberService;
import com.ottAll.ottAll.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/web/member")
public class MemberController {

    private final MemberService memberService;
    private final PollService pollService;

    /**
     * test
     * @return
     */
    @ResponseBody
    @GetMapping("/profile")
    public RetrieveProfileRes retrieveProfile(){
        System.out.println("---retrievePofile---");
        return new RetrieveProfileRes("hello");
    }

    /**
     * GET 설문조사 항목 조회
     * @return
     */
    @ResponseBody
    @GetMapping("/poll")
    public ResponseEntity<BaseResponse> retrievePollInfo(){

        try{
            RetrievePollDto retrievePollDto = pollService.retrievePollInfo();
            return new ResponseEntity<>(new BaseResponse(retrievePollDto), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }
    }
}
