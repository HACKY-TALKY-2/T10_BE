package com.channeltalk.teamten.member.entity;

import com.channeltalk.teamten.member.dto.MemberSignUpDto;
import com.channeltalk.teamten.post.entity.Participation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "member")
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_key_id", nullable = false)
    private Long memberKeyId;

    @Column(name = "user_id", nullable = false, unique = true, length = 50)
    private String userId;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "point", nullable = false)
    private Long point;

    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participationList = new ArrayList<>();

    // 신고 생성
    public static Member createMember(MemberSignUpDto memberSignUpDto) {

        return Member.builder()
                .userId(memberSignUpDto.getUserId())
                .password(memberSignUpDto.getPassword())
                .point(memberSignUpDto.getPoint())
                .location(memberSignUpDto.getLocation())
                .nickname(memberSignUpDto.getNickname())
                .build();
    }
}
