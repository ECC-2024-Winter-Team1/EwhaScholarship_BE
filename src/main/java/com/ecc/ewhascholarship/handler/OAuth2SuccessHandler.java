package com.ecc.ewhascholarship.handler;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.LoginResponseDto;
import com.ecc.ewhascholarship.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        String email = authentication.getName();

        // 이메일로 사용자 가입 여부 확인
        boolean isRegistered = userService.existsByEmail(email);

        String accessToken = null;
        if (isRegistered) {
            // 회원인 경우 JWT 생성
            accessToken = "token";
        }

        LoginResponseDto responseDto = new LoginResponseDto(isRegistered, email, accessToken);

        // json 문자열 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ApiResponse.success("로그인 성공!", responseDto));

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(jsonResponse);
    }
}
