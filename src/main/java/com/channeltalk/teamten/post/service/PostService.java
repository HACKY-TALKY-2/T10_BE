package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.post.dto.PostAddDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;

import java.io.IOException;

public interface PostService {
    void add(PostAddDto postAddDto) throws IOException;

    void update(String content, Long postId) throws IOException;

    Long join(PostJoinDto postJoinDto) throws IOException;

}
