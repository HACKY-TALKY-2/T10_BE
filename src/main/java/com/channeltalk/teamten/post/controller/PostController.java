package com.channeltalk.teamten.post.controller;

import com.channeltalk.teamten.post.dto.PostAddDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;
import com.channeltalk.teamten.post.dto.PostUpdateDto;
import com.channeltalk.teamten.post.entity.Post;
import com.channeltalk.teamten.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/post", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PostController {

    private final PostService postService;

    /**
     * 게시글 추가
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addReport(@ModelAttribute PostAddDto postAddDto) throws IOException {

        Map<String, Object> response = new HashMap<>();

        postService.add(postAddDto);

        String result = "asdf";

        if(result != null) {
            response.put("result", true);
            response.put("message", "Registeration Success");
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Registeration Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    /**
     * 게시글 업데이트
     */
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updatePost(@RequestBody PostUpdateDto request) throws IOException {


        postService.update(request.getContent(), request.getPostId());
        return createResponse(true, "Update Success", "Update Fail");
    }

    /**
     * 모든 게시글 리스트
     */
    @GetMapping("/all")
    public ResponseEntity<Object> allPost( ) throws IOException {

        Map<String, Object> response = new HashMap<>();
        List<Post> posts = postService.getAllPost();

        if(posts != null) {
            response.put("result", true);
            response.put("message", "조회 Success");
            response.put("contents", posts);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "조회 Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    /**
     * 공구 참여
     */
    @PostMapping("/join")
    public ResponseEntity<Object> joinPost(@RequestBody PostJoinDto request) throws IOException {

        Map<String, Object> response = new HashMap<>();
        String kakoLink = postService.join(request);

        if(kakoLink != null) {
            response.put("result", true);
            response.put("message", "Join Success");
            response.put("kakoLink", kakoLink);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Join Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
    }

    /**
     * 공구 마감
     */

    @PostMapping("/end")
    public ResponseEntity<Object> endPost(@RequestBody Long postId) throws IOException {

        Map<String, Object> response = new HashMap<>();
        postService.end(postId);

        response.put("result", true);
        response.put("message", "end Success");
        return ResponseEntity.ok(response);

    }

    // 응답 메서드
    private ResponseEntity<Map<String, Object>> createResponse(boolean result, String successMessage, String failMessage) {
        Map<String, Object> response = new HashMap<>();
        if (result) {
            response.put("result", true);
            response.put("message", successMessage);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", failMessage);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
