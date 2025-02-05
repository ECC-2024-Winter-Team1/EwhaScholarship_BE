package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.UserDto;
import com.ecc.ewhascholarship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserDto registerUser(UserDto dto) {
        User user = dto.toEntity();
        if (user.getId() != null) {
            return null;
        } else if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        User registered = userRepository.save(user);
        return UserDto.fromEntity(registered);
    }

}
