package com.channeltalk.teamten.post.controller;

import com.channeltalk.teamten.post.dto.PostDto;
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
    // 게시글 추가
    @PostMapping("/add")
    public ResponseEntity<Object> addReport(@ModelAttribute PostDto postDto) throws IOException {

        Map<String, Object> response = new HashMap<>();

        postService.add(postDto);

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
}
