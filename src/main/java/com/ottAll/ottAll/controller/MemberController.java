package com.ottAll.ottAll.controller;

import com.ottAll.ottAll.config.BaseResponse;
import com.ottAll.ottAll.dto.*;
import com.ottAll.ottAll.service.MemberService;
import com.ottAll.ottAll.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * GET 성향조사 항목 조회
     * @return
     */
    @ResponseBody
    @GetMapping("/poll")
    public ResponseEntity<BaseResponse> retrievePollInfo(){

        try{
            RetrievePollDto retrievePollDto = pollService.retrievePollInfo();
            return new ResponseEntity<>(new BaseResponse(retrievePollDto), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * POST 성향조사 항목 등록
     * @return
     */
//    @ResponseBody
//    @PostMapping("/poll")
//    public ResponseEntity<BaseResponse> createPollResult(HttpServletRequest request, @RequestBody CreatePollResultReq createPollResultReq){
//
//        try{
//            pollService.
//        } catch (Exception e){
//
//        }
//    }

    /**
     * POST 미디어 좋아요 등록
     * @param request
     * @param createLikeReq
     * @return
     */
    @ResponseBody
    @PostMapping("/like")
    public ResponseEntity<BaseResponse> createLike(HttpServletRequest request, @RequestBody CreateLikeReq createLikeReq){

        try{
            String userId = (String) request.getAttribute("userId");
            memberService.createLike(userId, createLikeReq.getMediaPk());
            return new ResponseEntity<>(new BaseResponse(HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * DELETE 미디어 좋아요 취소
     * @param request
     * @param deleteLikeReq
     * @return
     */
    @ResponseBody
    @DeleteMapping ("/like")
    public ResponseEntity<BaseResponse> createLike(HttpServletRequest request, @RequestBody DeleteLikeReq deleteLikeReq){

        try{
            String userId = (String) request.getAttribute("userId");
            memberService.deleteLike(userId, deleteLikeReq.getMediaPk());
            return new ResponseEntity<>(new BaseResponse(HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }
    }

}
