package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.post.dto.PostDto;

import java.io.IOException;

public interface PostService {
    void add(PostDto postDto) throws IOException;

}
