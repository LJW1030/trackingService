package com.tracking.api.common.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

import com.tracking.lib.exception.CommonRuntimeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

public class JwtManager {
    private static final String JWTS_USERNO = "userno";
    private static final String JWTS_AUTHKEY = "authkey";

    // 24시간으로 임시변경.
    private long ACCESS_TOKEN_VALIDATiON_SECOND = 1000 * 60 * 30 * 24;

    // 1개월
    private long REFRESH_TOKEN_VALIDATiON_SECOND = 1000 * 60 * 60 * 24 * 30;

    private String secretKey;

    public JwtManager(String secretKey) {
        this.secretKey = secretKey;
    }

    public JwtManager(String secretKey, Long accessTokenValidationSecond, Long refreshTokenValidationSecond) {
        this.secretKey = secretKey;
        this.ACCESS_TOKEN_VALIDATiON_SECOND = accessTokenValidationSecond;
        this.REFRESH_TOKEN_VALIDATiON_SECOND = refreshTokenValidationSecond;
    }

    private Key getSigninKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰 해석
    public Claims validTokenAndReturnBody(String token) {

        try {
            return Jwts.parserBuilder()
                .setSigningKey(getSigninKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
        }
        catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            // e.printStackTrace();
            // throw new InvalidParameterException("유효하지 않은 토큰입니다");
            throw new CommonRuntimeException("E2000", "유효하지 않은 토큰입니다.");
        }
    }

    // 토큰 인증기간 해석
    public String validTokenExpired(String token) {

        String result = "";

        try {

            Jwts.parserBuilder()
                .setSigningKey(getSigninKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

            result = "Success";
        }
        catch (ExpiredJwtException e) {
            result = "Expired";
            // throw new CommonRuntimeException(CommonErrorCode.AUTHENTICATION_FAIL_05);

        }
        catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            result = "Fail";
        }

        return result;
    }

    // 유저No 조회
    public String getUserNo(String token) {
        return validTokenAndReturnBody(token).get(JWTS_USERNO, String.class);
    }

    // 유저 auth_key 조회
    public String getUserAuthKey(String token) {
        return validTokenAndReturnBody(token).get(JWTS_AUTHKEY, String.class);
    }

    // access token 생성
    public String generateAccessToken(String userno, String authkey) {
        return doGenerateToken(userno, authkey, ACCESS_TOKEN_VALIDATiON_SECOND);
    }

    // refresh token 생성
    public String generateRefreshToken(String userno, String authkey) {
        return doGenerateToken(userno, authkey, REFRESH_TOKEN_VALIDATiON_SECOND);
    }

    // accessToken 유효시간 알림(second)
    public Long getValidationAccessTokenTime() {
        return ACCESS_TOKEN_VALIDATiON_SECOND;
    }

    private String doGenerateToken(String userno, String authkey, Long expireTime) {
        Claims claims = Jwts.claims();
        claims.put(JWTS_USERNO, userno);
        claims.put(JWTS_AUTHKEY, authkey);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime))
            .signWith(getSigninKey(secretKey), SignatureAlgorithm.HS512)
            .compact();
    }
}
