package com.channeltalk.teamten.post.service;

import com.channeltalk.teamten.member.entity.Member;
import com.channeltalk.teamten.member.repository.MemberRepository;
import com.channeltalk.teamten.post.dto.PostAddDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;
import com.channeltalk.teamten.post.entity.Participation;
import com.channeltalk.teamten.post.entity.Post;
import com.channeltalk.teamten.post.repository.ParticipationRepository;
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
    private final ParticipationRepository participationRepository;
    private final MemberRepository memberRepository;

    // 게시물 추가
    @Override
    public void add(PostAddDto postAddDto) throws IOException {

        MultipartFile multipartFile = postAddDto.getMultipartFile();

        if (multipartFile != null) {
            // S3 저장
            String imagePathList = s3Service.saveUploadFile(multipartFile);

            Post inputPost = Post.createPost(postAddDto, imagePathList);

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
    public String join(PostJoinDto postJoinDto) throws IOException {
        Optional<Post> beforePost = postRepository.findById(postJoinDto.getPostId());
        Post post = beforePost.get();
        Long participantPeopleCount = post.getParticipantPeople(); // 현재 참여자

        Long changeCount = participantPeopleCount + postJoinDto.getBuyCount();
        post.setParticipantPeople(changeCount); // 참여자 업데이트

        // Member찾기
        Optional<Member> memberOptionalr = memberRepository.findById(postJoinDto.getMemberKeyId());
        Member member = memberOptionalr.get();

        // 참여자 리스트 생성
        Participation participation = Participation.createParticipation(postJoinDto.getPostId(), postJoinDto.getBuyCount(), member );

        //member에 participation 추가
        member.getParticipationList().add(participation);

        memberRepository.save(member);
        postRepository.save(post);

        return post.getKakao();
    }

    // 모든 게시글 불러오기
    @Override
    public List<Post> getAllPost() {
        List<Post> reports = postRepository.findAll();

        return reports;
    }

    // 공구 마감하기
    @Override
    public void end(Long postId) throws IOException {

        Optional<Post> optionalPost = postRepository.findById(postId);
        Post post = optionalPost.get();
        post.setDeadline(1L); // 공구마감

        postRepository.save(post);

    }

    @Override
    public List<Post> getDeadPost(Long memberKeyId) {

        List<Post> reports = postRepository.findByMemberKeyIdAndDeadline(memberKeyId, 1L); // 마감된 공구리스트만

        return reports;
    }


}
