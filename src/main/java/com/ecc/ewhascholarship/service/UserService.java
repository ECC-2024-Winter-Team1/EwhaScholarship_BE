package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.UserDto;
import com.ecc.ewhascholarship.exception.UserNotFoundException;
import com.ecc.ewhascholarship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 사용자 조회
    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return UserDto.fromEntity(user);
    }

    // 사용자 등록
    public UserDto registerUser(UserDto dto) {
        User user = dto.toEntity();

        if (user.getId() != null) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        if (!isValidEmailDomain(user.getEmail())) {
            throw new IllegalArgumentException("허용된 이메일 도메인이 아닙니다.");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        User registered = userRepository.save(user);
        return UserDto.fromEntity(registered);
    }

    // 사용자 수정
    public UserDto updateUser(UUID id, UserDto dto) {
        User user = dto.toEntity();
        User target = userRepository.findById(id).orElse(null);

        if (target == null || id != target.getId()) {
            throw new UserNotFoundException();
        }

        target.patch(user);
        User updated = userRepository.save(target);
        return UserDto.fromEntity(updated);
    }

    // 사용자 삭제
    public void deleteUser(UUID id) {
        User target = userRepository.findById(id).orElse(null);
        if (target == null || id != target.getId()) {
            throw new UserNotFoundException();
        }
        userRepository.delete(target);
    }

    // 이메일 도메인 체크
    private boolean isValidEmailDomain(String email) {
        String emailDomain = email.substring(email.indexOf('@') + 1);
        return "ewha.ac.kr".equals(emailDomain) || "ewhain.net".equals(emailDomain);
    }

}
