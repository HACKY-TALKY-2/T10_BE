package com.channeltalk.teamten.member.service;

import com.channeltalk.teamten.member.dto.MemberLoginDto;
import com.channeltalk.teamten.member.dto.MemberSignUpDto;
import com.channeltalk.teamten.member.entity.Member;
import com.channeltalk.teamten.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    /**
     * 회원가입
     */
    @Override
    public void signUp(MemberSignUpDto memberSignUpDto) throws IOException {

        Member member = Member.createMember(memberSignUpDto);
        memberRepository.save(member);
    }

    /**
     * 로그인
     *
     */
    @Override
    public boolean login(MemberLoginDto memberLoginDto) throws IOException {

        Optional<Member> member = memberRepository.findByUserIdAndPassword(memberLoginDto.getUserId(), memberLoginDto.getPassword());

        if (member.isPresent())
            return true;
        return false;
    }

}
