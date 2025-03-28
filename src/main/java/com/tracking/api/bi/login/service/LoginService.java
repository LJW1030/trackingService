package com.tracking.api.bi.login.service;

import com.tracking.api.bi.login.dto.LoginReqDto.ProcLoginReq;
import com.tracking.api.bi.login.dto.LoginResDto.ProcLoginRes;

public interface LoginService {
	ProcLoginRes login(ProcLoginReq req);
}