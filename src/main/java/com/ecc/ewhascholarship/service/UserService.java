package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.*;
import com.ecc.ewhascholarship.exception.UserNotFoundException;
import com.ecc.ewhascholarship.repository.UserRepository;
import com.ecc.ewhascholarship.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 사용자 조회
    public UserResponseDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return UserResponseDto.fromEntity(user);
    }

    // 사용자 등록
    public UserResponseDto registerUser(UserRegisterDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = dto.toEntity(encodedPassword);

        if (user.getId() != null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        User registered = userRepository.save(user);
        return UserResponseDto.fromEntity(registered);
    }

    // 사용자 수정
    public UserResponseDto updateUser(UUID id, UserUpdateDto dto) {
        User user = dto.toEntity();
        System.out.println(user.toString());
        User target = userRepository.findById(id).orElse(null);

        if (target == null || id != target.getId()) {
            throw new UserNotFoundException();
        }

        target.patch(user);
        User updated = userRepository.save(target);
        return UserResponseDto.fromEntity(updated);
    }

    // 사용자 삭제
    public void deleteUser(UUID id) {
        User target = userRepository.findById(id).orElse(null);
        if (target == null || id != target.getId()) {
            throw new UserNotFoundException();
        }
        userRepository.delete(target);
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.getUsername()).orElse(null);

        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호 입니다.");
        }

        return new LoginResponseDto(jwtTokenProvider.createToken(user.getId()));
    }
}
