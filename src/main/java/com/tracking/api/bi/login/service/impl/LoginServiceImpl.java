package com.tracking.api.bi.login.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tracking.api.bi.login.dto.LoginReqDto.ProcLoginReq;
import com.tracking.api.bi.login.dto.LoginResDto.ProcLoginRes;
import com.tracking.api.bi.login.repository.LoginRepository;
import com.tracking.api.bi.login.service.LoginService;
import com.tracking.api.common.jwt.JwtManager;
import com.tracking.lib.constants.ResultCodeConstants;
import com.tracking.lib.domain.Users;
import com.tracking.lib.dto.user.UsersDto;
import com.tracking.lib.property.JwtProperty;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public ProcLoginRes login(ProcLoginReq req) {
    	ProcLoginRes result = new ProcLoginRes();
    	
    	Optional<Users> userOptional = loginRepository.findByUserIdAndPw(req.getUserId(), req.getUserPw());

    	Users user = userOptional.orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다."));
    	
    	if (StringUtils.hasText(user.getUserNo())) {

            JwtManager jwtManager = new JwtManager(JwtProperty.SECRET, JwtProperty.ACCESS_EXPIRATION_TIME, JwtProperty.REFRESH_EXPIRATION_TIME);

            String accessToken = jwtManager.generateAccessToken(user.getUserNo(), user.getAuthKey());
            
            UsersDto userDto = req.toUsers(user, accessToken);

			/*
			 * // 로그인 성공시 로그인 이력 정보 저장하기 LoginLogVo loginLogVo = new LoginLogVo();
			 * 
			 * // 오늘날짜와 시간 세팅 LocalDateTime currentDateTime = LocalDateTime.now();
			 * 
			 * loginLogVo.setUsrNo(userInfo.getUsrNo());
			 * loginLogVo.setLoginDtime(currentDateTime);
			 * loginLogVo.setUsrId(userInfo.getUsrId());
			 * loginLogVo.setLoginIpAddr(req.getClientIp());
			 * loginLogVo.setLoginChnlCd(req.getLoginChnlCd());
			 * 
			 * // 로그인 로그 저장 - 로그생성 일시 중지 int insResult =
			 * loginMapper.insertLoginLog(loginLogVo.toLoginLog());
			 */

            result.setResultCode(ResultCodeConstants.RESULT_SUCCESS_CODE);
            result.setResultMsg(ResultCodeConstants.RESULT_SUCCESS_MSG);
            result.setUser(userDto);
            //result.setLoginLog(loginLogVo);

            result.setResult(true);

        }
        else {
            result.setResultCode(ResultCodeConstants.RESULT_FAIL_CODE);
            result.setResultMsg(ResultCodeConstants.RESULT_FAIL_MSG);
            result.setResult(false);
            result.setErrorCode(ResultCodeConstants.RESULT_LOGIN_EXCEPTION_CODE);
            result.setErrorMsg(ResultCodeConstants.RESULT_LOGIN_EXCEPTION_MSG);
        }
    	
        return result;
    }
}