package com.ottAll.ottAll.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ottAll.ottAll.util.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = jwtUtil.getJwt();

//        String requestURI= request.getRequestURI();

        //jwt가 유효한 경우
        try{

            if(accessToken == null){
                throw new IllegalArgumentException("Access Token이 없습니다.");
            }

            String userId = jwtUtil.validateAccessTokenAndGetUserId(accessToken);
            request.setAttribute("userId", userId);
            return true;

        } catch (IllegalArgumentException e){

            Map<String, String> map = new HashMap<>();
            map.put("message", e.getMessage());

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            response.getWriter().write(json);

            return false;

        } catch (ExpiredJwtException e){

            Map<String, String> map = new HashMap<>();
            map.put("message", "만료된 토큰입니다.");
            map.put("redirectURI", "/web/auth/reissue");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            response.getWriter().write(json);

            return false;

        } catch (Exception e){

            Map<String, String> map = new HashMap<>();
            map.put("message", "유효하지 않은 토큰입니다.");
            map.put("redirectURI", "/web/auth/login");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            response.getWriter().write(json);
            return false;
        }

    }
}
