package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.post.dto.PostDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;

import java.io.IOException;

public interface PostService {
    void add(PostDto postDto) throws IOException;

    void update(String content, Long postId) throws IOException;

    Long join(PostJoinDto postJoinDto) throws IOException;

}
