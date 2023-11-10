package com.channeltalk.teamten.entity;

import com.channeltalk.teamten.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "post_post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private Integer postId;

    @Column(name = "user_id", nullable = false, length = 40)
    private String userId;

    @Column(nullable = false, length = 45)
    private String title;

    @Column(nullable = false, length = 45)
    private String content;

    @Column(length = 45)
    private String image;

    @Column(name = "itemPrice", nullable = false)
    private Integer itemPrice;

    @Column(name = "tradePlace", nullable = false, length = 45)
    private String tradePlace;

    @Column(nullable = false, length = 50, unique = true)
    private String kakao;

    @Column(name = "totalPeople", nullable = false)
    private Integer totalPeople;

    @Column(name = "participantPeople", nullable = false)
    private Integer participantPeople;

    @Column(name = "totalItemCount", nullable = false)
    private Integer totalItemCount;

    @Column(nullable = false, length = 45)
    private String location;

}