package com.ottAll.ottAll.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class BaseResponse<T>{

    private final Boolean isSuccess;
    private final int code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public BaseResponse(){
        this.isSuccess = true;
        this.code = HttpStatus.OK.value();
        this.message = "요청이 완료되었습니다";
        this.result = null;
    }

    public BaseResponse(T result){
        this.isSuccess = true;
        this.code = HttpStatus.OK.value();
        this.message = "요청이 완료되었습니다";
        this.result = result;
    }

    public BaseResponse(HttpStatus httpStatus, Exception e, T result){

        this.code = httpStatus.value();
        this.result = result;

        if(httpStatus.is2xxSuccessful()){
            this.isSuccess = true;
            this.message = "요청이 완료되었습니다.";

        }else{
            this.isSuccess = false;
            if(e == null) {
                this.message = "오류가 있습니다";
            }else{
                this.message = e.getMessage();
            }
        }


    }

    public BaseResponse(HttpStatus badResponse, Exception e) {
        this.isSuccess = false;
        this.code = badResponse.value();
        this.message = e.getMessage();
        this.result = null;
    }
}
