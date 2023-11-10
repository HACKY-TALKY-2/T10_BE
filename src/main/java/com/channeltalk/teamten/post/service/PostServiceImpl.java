package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.post.dto.PostDto;
import com.channeltalk.teamten.post.entity.Post;
import com.channeltalk.teamten.post.repository.PostRepository;
import com.channeltalk.teamten.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final S3Service s3Service;
    private final PostRepository postRepository;
    @Override
    public void add(PostDto postDto) throws IOException {

        MultipartFile multipartFile = postDto.getMultipartFile();

        if (multipartFile != null) {
            // S3 저장
            String imagePathList = s3Service.saveUploadFile(multipartFile);

            Post inputPost = Post.createPost(postDto, imagePathList);
            System.out.println("@@@@@@@@@@@@");
            System.out.println(inputPost.getItemPrice());

            postRepository.save(inputPost);

        }

    }
}
