package com.tracking.api.bi.user.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "user API", description = "user API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public String getUserInfo() {
        return "현재 위치 정보";
    }
}
