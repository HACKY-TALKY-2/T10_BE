package com.channeltalk.teamten.post.entity;

import com.channeltalk.teamten.BaseTimeEntity;
import com.channeltalk.teamten.post.dto.PostAddDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "post_post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private Long postId;

    @Column(name = "member_key_id", nullable = false)
    private Long memberKeyId;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String content;

    @Column(length = 100)
    private String image;

    @Column(name = "itemPrice", nullable = false)
    private Long itemPrice;

    @Column(name = "tradePlace", nullable = false, length = 45)
    private String tradePlace;

    @Column(nullable = false, length = 50, unique = true)
    private String kakao;

    @Column(name = "totalPeople", nullable = false)
    private Long totalPeople;

    @Column(name = "participantPeople", nullable = false)
    private Long participantPeople;

    @Column(name = "totalItemCount", nullable = false)
    private Long totalItemCount;

    @Column(nullable = false, length = 45)
    private String location;

    @Column(name = "shopLink", nullable = false)
    private String shopLink;


    public static Post createPost(PostAddDto postAddDto, String imageUrl) {

        return Post.builder()
                .memberKeyId(postAddDto.getMemberKeyId())
                .title(postAddDto.getTitle())
                .content(postAddDto.getContent())
                .itemPrice(postAddDto.getItemPrice())
                .tradePlace(postAddDto.getTradePlace())
                .kakao(postAddDto.getKakao())
                .totalPeople(postAddDto.getTotalPeople())
                .participantPeople(0L)
                .totalItemCount(postAddDto.getTotalItemCount())
                .location(postAddDto.getLocation())
                .image(imageUrl)
                .shopLink(postAddDto.getShopLink())
                .build();
    }
}