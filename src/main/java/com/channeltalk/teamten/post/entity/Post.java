package com.channeltalk.teamten.post.entity;

import com.channeltalk.teamten.BaseTimeEntity;
import com.channeltalk.teamten.post.dto.PostDto;
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

    @Column(name = "user_id", nullable = false, length = 40)
    private String userId;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String content;

    @Column(length = 100)
    private String image;

    @Column(name = "itemPrice", nullable = false)
    private Long itemPrice = 0L;

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


    public static Post createPost(PostDto postDto, String imageUrl) {

        return Post.builder()
                .userId(postDto.getUserId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .itemPrice(postDto.getItemPrice())
                .tradePlace(postDto.getTradePlace())
                .kakao(postDto.getKakao())
                .totalPeople(postDto.getTotalPeople())
                .participantPeople(0L)
                .totalItemCount(postDto.getTotalItemCount())
                .location(postDto.getLocation())
                .image(imageUrl)
                .build();
    }
}