package com.channeltalk.teamten.member.controller;

import com.channeltalk.teamten.member.dto.MemberChargeDto;
import com.channeltalk.teamten.member.dto.MemberLoginDto;
import com.channeltalk.teamten.member.dto.MemberSignUpDto;
import com.channeltalk.teamten.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public void signUp(@RequestBody MemberSignUpDto memberSignUpDto) throws IOException {
       memberService.signUp(memberSignUpDto);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody MemberLoginDto memberLoginDto) throws IOException {
        Long result = memberService.login(memberLoginDto );

        Map<String, Object> response = new HashMap<>();

        if(result != -1L) {
            response.put("result", true);
            response.put("message", "Login Success");
            response.put("memberKeyId", result);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Login Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    /**
     * 포인트 충전
     */
    @PostMapping("/charge")
    public ResponseEntity<Object>  charge(@RequestBody MemberChargeDto memberChargeDto) throws IOException {
        String result = memberService.charge(memberChargeDto);

        Map<String, Object> response = new HashMap<>();

        if(result.equals("적용완료") ) {
            response.put("result", true);
            response.put("message", "charge Success");
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "잔액이 부족합니다");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }


}
