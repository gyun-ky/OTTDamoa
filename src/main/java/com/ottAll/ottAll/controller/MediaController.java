package com.ottAll.ottAll.controller;

import com.ottAll.ottAll.config.BaseResponse;
import com.ottAll.ottAll.dao.MediaDetailDao;
import com.ottAll.ottAll.service.MediaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/web/media")
public class MediaController {

    private final MediaService mediaService;

    @ResponseBody
    @GetMapping("/{mediaId}")
    public ResponseEntity<BaseResponse> retrieveMediaDetail(HttpServletRequest request, @PathVariable("mediaId") Long mediaId){
        try{
            String userId = (String) request.getAttribute("userId");
            MediaDetailDao mediaDetailDao = mediaService.retrieveMediaDetail(mediaId, userId);
            return new ResponseEntity<>(new BaseResponse(mediaDetailDao), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(HttpStatus.BAD_REQUEST, e), HttpStatus.BAD_REQUEST);
        }
    }
}
