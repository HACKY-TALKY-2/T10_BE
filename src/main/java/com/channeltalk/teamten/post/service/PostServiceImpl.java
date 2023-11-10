package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.post.dto.PostDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;
import com.channeltalk.teamten.post.entity.Post;
import com.channeltalk.teamten.post.repository.PostRepository;
import com.channeltalk.teamten.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final S3Service s3Service;
    private final PostRepository postRepository;

    // 게시물 추가
    @Override
    public void add(PostDto postDto) throws IOException {

        MultipartFile multipartFile = postDto.getMultipartFile();

        if (multipartFile != null) {
            // S3 저장
            String imagePathList = s3Service.saveUploadFile(multipartFile);

            Post inputPost = Post.createPost(postDto, imagePathList);

            postRepository.save(inputPost);

        }

    }

    // 게시물 업데이트
    @Override
    public void update(String content, Long postId) throws IOException {
        Optional<Post> updatePost = postRepository.findById(postId);

        Post post = updatePost.get();
        post.setContent(content);

        postRepository.save(post);
    }

    // 공구참여
    @Override
    public Long join(PostJoinDto postJoinDto) throws IOException {
        Optional<Post> updatePost = postRepository.findById(postJoinDto.getPostId());
        Post post = updatePost.get();
        Long poesonCount = post.getParticipantPeople();

        Long changeCount = poesonCount + postJoinDto.getBuyCount();
        post.setParticipantPeople(changeCount);

        postRepository.save(post);
        return changeCount;
    }


}
