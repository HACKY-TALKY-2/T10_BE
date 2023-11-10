package com.channeltalk.teamten.post.entity;

import com.channeltalk.teamten.member.entity.Member;
import com.channeltalk.teamten.post.dto.PostAddDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "participation")
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "postID", nullable = false)
    private Long postId;

    @Column(name = "count", nullable = false)
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_key_id")
    private Member member;

    public static Participation createParticipation(Long postId, Long count, Member member) {

        return Participation.builder()
                .postId(postId)
                .count(count)
                .member(member)
                .build();
    }
}
