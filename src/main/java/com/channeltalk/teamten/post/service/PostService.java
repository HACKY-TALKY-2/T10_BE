package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.post.dto.PostAddDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;
import com.channeltalk.teamten.post.entity.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {
    void add(PostAddDto postAddDto) throws IOException;

    void update(String content, Long postId) throws IOException;

    String join(PostJoinDto postJoinDto) throws IOException;

    // 모든 신고 내역 조회
    List<Post> getAllPost();

    // 공구 마감
    void end(Long postId) throws IOException;

}
