/*
 * This software is the confidential and proprietary information of UZEN Shinsegae Internatinal Inc.
 * You shall not disclose such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with UZEN.
 */
package com.tracking.api.common.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @Class Name : CommonUtils.java
 * @Description : common util
 * @author UZEN /
 * @since 2015. 10. 7.
 * @version 1.0
 * @see Copyright(c) 2015 UZEN. All rights reserved
 */
public class CommonUtils {
    public static Model makeGridData(Model model, List<?> list, String total) {

        if (list != null) {
            model.addAttribute("total", total);
            model.addAttribute("records", list);
            model.addAttribute("satus", "success");
        }
        else {
            model.addAttribute("total", "0");
            model.addAttribute("records", list);
            model.addAttribute("satus", "error");
        }
        return model;
    }

    /**
     * MD5 인코딩
     * 
     * @param baseString
     * @return
     */
    public static String encMd5(String baseString) {
        String resultString = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(baseString.getBytes());

            for (int i = 0; i < digest.length; i++) {
                resultString = resultString + Integer.toHexString(digest[i] & 0xFF);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * SHA-1 인코딩
     * 
     * @param baseString
     * @return
     */
    public static String encSha1(String baseString) {
        String resultString = "";

        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] digest = sha1.digest(baseString.getBytes());

            for (int i = 0; i < digest.length; i++) {
                resultString = resultString + Integer.toHexString(digest[i] & 0xFF);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * SAH256 인코딩
     * 
     * @param baseString
     * @return
     */
    public static String encSha256(String baseString) {
        String resultString = "";

        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha256.digest(baseString.getBytes());

            for (int i = 0; i < digest.length; i++) {
                resultString = resultString + Integer.toHexString(digest[i] & 0xFF);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * Hmac sha1 인코딩
     * 
     * @param keyString
     * @param baseString
     * @return
     */
    public static String encMacSha1(String keyString, String baseString) {
        String resultString = "";

        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(key);
            byte[] bytes = mac.doFinal(baseString.getBytes("UTF-8"));

            // String resultString = new String(Base64.encodeBase64(bytes));
            for (int i = 0; i < bytes.length; i++) {
                resultString = resultString + Integer.toHexString(bytes[i] & 0xFF);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    private static final String[] HEADERS_TO_TRY = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};

    /**
     * 접속자 IP 얻기
     * 
     * @param request
     * @return
     */
    public static String getClientIpAddress(HttpServletRequest request) {

        for (String header : HEADERS_TO_TRY) {
            String ip = request.getHeader(header);

            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 비밀번호 encoding
     * 
     * @param baseString
     * @return
     */
    public static String encPasswordString(String baseString) {
        String resultString = encMd5(baseString);
        resultString = encSha256(resultString);
        return resultString;
    }

    /**
     * list가 null이거나 size가 0이면 true
     * 
     * @param list
     * @return
     */
    public static boolean isEmptyList(List<?> list) {

        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

    /**
     * 두 날짜의 차이
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int getDiffByDay(String str1, String str2) {
        int diffInDays = 0;

        try {
            Calendar calendar1;
            Calendar calendar2;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            Date date1 = sdf.parse(str1);
            calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);

            Date date2 = sdf.parse(str2);
            calendar2 = Calendar.getInstance();
            calendar2.setTime(date2);

            diffInDays = (int) ((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 3600 * 24));
        }
        catch (java.text.ParseException e) {
            System.err.println("ParseException ");
        }
        return diffInDays;
    }

    /**
     * <h3>대상문자열의 왼쪽에 지정한 대체문자를 체워서 지정한 길이의 문자열을 반환한다.</h3>
     * <p>
     * 여기서 길이는 {@link java.lang.String#length()} 매소드를 사용하여 처리된다.
     * </p>
     *
     * @param str 대상문자열
     * @param len 길이
     * @param addStr 대체문자
     * @return 지정한 길이만큼의 문자열
     * @see #lpadByte(String, int, String)
     */

    public static String lpad(String str, int len, String addStr) {

        if (str == null) {
            str = "";
        }
        String result = str;
        int templen = len - result.length();

        for (int i = 0; i < templen; i++) {
            result = addStr + result;
        }

        return result;
    }
}
