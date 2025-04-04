package com.tracking.api.bi.login.dto;

import java.time.LocalDateTime;

import com.tracking.lib.domain.Users;
import com.tracking.lib.dto.common.BaseDto;
import com.tracking.lib.dto.user.UsersDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

public interface LoginResDto {

    @Data
    @EqualsAndHashCode(callSuper = false)
    @Schema(name = "ProcLoginRes", description = "로그인 응답 모델")
    public class ProcLoginRes extends BaseDto {

    	@Schema(description = "사용자정보")
        private UsersDto user;

//        @Schema(description = "로그인이력")
//        private LoginLogVo loginLog;

    }

}
