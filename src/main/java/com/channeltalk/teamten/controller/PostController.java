package com.channeltalk.teamten.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/post", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PostController {
    @GetMapping("")
    public String test() {
        return "dfdf";
    }
}
