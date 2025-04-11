package com.tracking.api.bi.login.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tracking.api.bi.login.dto.LoginReqDto.ProcLoginReq;
import com.tracking.api.bi.login.dto.LoginResDto.ProcLoginRes;
import com.tracking.api.bi.login.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
@Tag(name = "로그인 API", description = "로그인 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@Slf4j
public class LoginController {

	private final LoginService loginService;
    /**
     * Description : Login Process
     * 
     * @param ProcLoginReq reqLogin
     * @return ProcLoginRes
     */
    @Operation(summary = "로그인 처리", description = "로그인 처리를 실행한다.")
    @PostMapping("/proc-login")
    public ResponseEntity<ProcLoginRes> procLogin(@RequestBody @Validated ProcLoginReq req) {
    	log.info("로그인 요청: {}", req);
        // 로그인 회원 정보 조회 값 담기
        ProcLoginRes result = loginService.login(req);

        log.info("로그인 응답: {}", result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
