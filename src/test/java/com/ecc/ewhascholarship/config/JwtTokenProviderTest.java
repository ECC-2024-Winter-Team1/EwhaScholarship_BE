package com.ecc.ewhascholarship.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private UUID userId;

    @BeforeEach
    public void setUserId() {
        userId = UUID.randomUUID();
    }

    @Test
    public void createToken() {
        // given
        String token = jwtTokenProvider.createToken(userId);

        // when
        // createToken 호출

        // then
        Assertions.assertThat(token).isNotNull();
    }

    @Test
    public void getUserIdFromToken() {
        // given
        String token = jwtTokenProvider.createToken(userId);

        // when
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        // then
        Assertions.assertThat(userId).isEqualTo(this.userId.toString());
    }

    @Test
    public void isTokenExpired_토큰_만료_안됨() {
        // given
        String token = jwtTokenProvider.createToken(userId);

        // when
        boolean isExpired = jwtTokenProvider.isTokenExpired(token);

        // then
        Assertions.assertThat(isExpired).isFalse();
    }

    @Test
    public void isTokenExpired_토큰_만료됨() {
        // given
        jwtTokenProvider.setValidityInMilliseconds(1000);
        String token = jwtTokenProvider.createToken(userId);

        // 1초 기다림
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // when
        boolean isExpired = jwtTokenProvider.isTokenExpired(token);

        // then
        Assertions.assertThat(isExpired).isTrue();
    }
}
