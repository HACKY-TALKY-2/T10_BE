package com.channeltalk.teamten.post.controller;

import com.channeltalk.teamten.post.dto.PostAddDto;
import com.channeltalk.teamten.post.dto.PostJoinDto;
import com.channeltalk.teamten.post.dto.PostUpdateDto;
import com.channeltalk.teamten.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
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
     * 공구 참여
     */
    @PostMapping("/join")
    public ResponseEntity<Object> joinPost(@RequestBody PostJoinDto request) throws IOException {

        Map<String, Object> response = new HashMap<>();
        Long changedCount = postService.join(request);

        if(changedCount != null) {
            response.put("result", true);
            response.put("message", "Join Success");
            response.put("바뀐 count", changedCount);
            return ResponseEntity.ok(response);
        } else {
            response.put("result", false);
            response.put("message", "Join Fail");
            return ResponseEntity.badRequest().body(response); // 400 Bad Request
        }
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
