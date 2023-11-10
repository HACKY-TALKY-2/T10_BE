package com.channeltalk.teamten.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignUpDto {

    private String userId;
    private String password;
    private String nickname;
    private Long point = 0L;
    private String location;

}
