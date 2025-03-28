package com.tracking.api.bi.login.dto;

import com.tracking.lib.domain.Users;
import com.tracking.lib.dto.common.BaseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

public interface LoginResDto {

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Schema(name = "ProcLoginRes", description = "로그인 응답 모델")
    public class ProcLoginRes extends BaseDto {

        @Schema(description = "사용자정보")
        private Users userInfo;

    }

}
