package com.tracking.api.bi.login.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tracking.api.bi.login.dto.LoginReqDto.ProcLoginReq;
import com.tracking.api.bi.login.dto.LoginResDto.ProcLoginRes;
import com.tracking.api.bi.login.repository.LoginRepository;
import com.tracking.api.bi.login.service.LoginService;
import com.tracking.lib.domain.Users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public ProcLoginRes login(ProcLoginReq req) {
    	Optional<Users> userOptional = loginRepository.findByUserIdAndPw(req.getUserId(), req.getUserPw());

    	Users user = userOptional.orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다."));
    	
        ProcLoginRes res = new ProcLoginRes();
        res.setUserInfo(user);

        return res;
    }
}