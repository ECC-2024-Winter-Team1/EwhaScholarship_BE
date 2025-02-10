package com.ecc.ewhascholarship.controller;

import com.ecc.ewhascholarship.common.ApiResponse;
import com.ecc.ewhascholarship.dto.UserDto;
import com.ecc.ewhascholarship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserDto>> getUser(Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        UserDto response = userService.getUserById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("사용자 정보 조회 성공!",response));
    }

}
