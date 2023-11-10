package com.channeltalk.teamten.member.service;

import com.channeltalk.teamten.member.dto.MemberChargeDto;
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
     */
    @Override
    public boolean login(MemberLoginDto memberLoginDto) throws IOException {

        Optional<Member> member = memberRepository.findByUserIdAndPassword(memberLoginDto.getUserId(), memberLoginDto.getPassword());

        if (member.isPresent())
            return true;
        return false;
    }

    /**
     * 포인트 충전
     */
    @Override
    public String charge(MemberChargeDto memberChargeDto) throws IOException {
        Optional<Member> optionalMember = memberRepository.findById(memberChargeDto.getMemberKeyId());
        Member member = optionalMember.get();

        Long prevPoint = member.getPoint();

        if (memberChargeDto.getType().equals("PLUS"))
            member.setPoint(prevPoint + memberChargeDto.getAddPoint());
        else if (memberChargeDto.getType().equals("MINUS"))
        {
            if (prevPoint - memberChargeDto.getAddPoint() < 0)
                return "잔액부족";
            else
                member.setPoint(prevPoint - memberChargeDto.getAddPoint());
        }
        memberRepository.save(member);
        return "적용완료";
    }

}
