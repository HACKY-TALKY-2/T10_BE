package com.channeltalk.teamten.post.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostAddDto {

    // postid만 없음
    // image도 없음
    private Long memberKeyId;
    private String title;
    private String content;
    private Long itemPrice;
    private String tradePlace;
    private String kakao;
    private Long totalPeople;
//    private Long participantPeople;
    private Long totalItemCount;
    private String location;

    private MultipartFile multipartFile; // 업로드 이미지
}
