package com.ottAll.ottAll.service;

import com.ottAll.ottAll.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JwtUtil jwtUtil;

}
