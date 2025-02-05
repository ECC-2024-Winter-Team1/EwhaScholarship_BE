package com.ecc.ewhascholarship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterResponseDto {
    private String accessToken;
    private UserDto user;
}
