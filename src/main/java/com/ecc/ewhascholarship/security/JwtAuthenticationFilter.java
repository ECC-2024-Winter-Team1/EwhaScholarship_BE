package com.ecc.ewhascholarship.security;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.exception.JwtAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            log.info("JWT 인증 필터가 요청을 처리합니다.");

            String token = getTokenFromRequest(request);

            if (token == null) {
                log.error("JWT 토큰이 없습니다.");
                throw new JwtAuthenticationException("로그인되지 않은 사용자입니다.");  // 예외 발생
            }

            if (!jwtTokenProvider.validateToken(token)) {
                log.error("유효하지 않은 JWT 토큰입니다.");
                throw new JwtAuthenticationException("유효하지 않은 토큰입니다.");  // 예외 발생
            }

            log.info("JWT 토큰이 유효합니다. 사용자 정보를 추출하여 인증 설정.");

            String userId = jwtTokenProvider.getUserIdFromToken(token);
            Authentication authentication = new OAuth2AuthenticationToken(
                    new OAuth2UserPrincipal(userId, null),
                    null, "jwt"
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("SecurityContext에 인증 정보가 설정되었습니다. userId: {}", userId);

            filterChain.doFilter(request, response);  // 계속 진행
            log.info("JWT 인증 필터가 요청 처리를 마쳤습니다.");
        } catch (JwtAuthenticationException e) {
            response.setStatus(401);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(response.getWriter(), ApiResponse.error(e.getMessage()));
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            log.debug("Authorization 헤더에서 Bearer 토큰 추출: {}", header);
            return header.substring(7);
        }
        return null;
    }

}
