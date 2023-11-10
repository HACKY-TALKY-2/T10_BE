package com.channeltalk.teamten.member.service;

import com.channeltalk.teamten.member.dto.MemberLoginDto;
import com.channeltalk.teamten.member.dto.MemberSignUpDto;
import com.channeltalk.teamten.member.entity.Member;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {

    //회원가입
    void signUp(MemberSignUpDto memberSignUpDto) throws IOException;

    //로그인
    boolean login(MemberLoginDto memberLoginDto) throws IOException;

}
