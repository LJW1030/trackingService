package com.tracking.api.bi.tracking;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "tracking API", description = "tracking API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tracking")
public class TrackingController {
    @GetMapping
    public String getTrackingInfo() {
        return "현재 위치 정보";
    }
}
