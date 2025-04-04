package com.tracking.api.bi.login.dto;

import java.io.Serializable;

import com.tracking.lib.domain.Users;
import com.tracking.lib.dto.user.UsersDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public interface LoginReqDto {

    @Data
    @Schema(name = "ProcLoginReq", description = "BO로그인 요청모델")
    public class ProcLoginReq implements Serializable {

        private static final long serialVersionUID = -8479867780657669160L;

        @Schema(description = "사용자 아이디")
        @NotBlank(message = "userId is not null")
        private String userId;

        @Schema(description = "패스워드")
        @NotBlank(message = "password is not null")
        private String userPw;

        public UsersDto toUsers(Users user, String accessToken) {
            return UsersDto.builder()
            	.userNo(user.getUserNo())
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .authKey(user.getAuthKey())
                .accessToken(accessToken)
                .build();

        }

    }

	/*
	 * @Data
	 * 
	 * @Schema(name = "LoginHistListReq", description = "로그인이력목록 조회요청모델") class
	 * LoginHistListReq {
	 * 
	 * @NotBlank(message = "popCheckNo is not blank")
	 * 
	 * @Schema(description = "체크번호") private String popCheckNo;
	 * 
	 * public LoginHistSearchVo toSearchCondition(Pagination pagination) { return
	 * LoginHistSearchVo.builder() .usrNo(this.popCheckNo)
	 * .pagination(CommonSearchRequestUtil.setPageInfo(pagination)) .build();
	 * 
	 * }
	 * 
	 * }
	 */

}
