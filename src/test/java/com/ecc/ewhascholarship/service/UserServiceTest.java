package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.User;
import com.ecc.ewhascholarship.dto.UserDto;
import com.ecc.ewhascholarship.exception.UserNotFoundException;
import com.ecc.ewhascholarship.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private UUID userId;

    @BeforeEach
    void setUp() {
        testUser = userRepository.save(User.builder()
                .email("test@ewha.ac.kr")
                .department("엘텍공과대학")
                .gpa(3.75f)
                .year(2)
                .incomeLevel(3)
                .build()
        );
        userId = testUser.getId();
    }

    @Test
    @Transactional
    void getUserById_성공() {
        System.out.println(testUser.toString());
        // when
        UserDto user = userService.getUserById(userId);

        // then
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(testUser.getEmail());
    }

    @Test
    @Transactional
    void getUserById_실패() {
        // given
        UUID randomId = UUID.randomUUID();

        // when & then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(randomId));
    }

    @Test
    @Transactional
    void registerUser_성공() {
        // given
        UserDto newUser = new UserDto(null, "newuser@ewha.ac.kr", "경영학과", 4.0f, 3, 2);

        // when
        UserDto registeredUser = userService.registerUser(newUser);

        // then
        assertThat(registeredUser).isNotNull();
        assertThat(registeredUser.getEmail()).isEqualTo("newuser@ewha.ac.kr");
    }

    @Test
    @Transactional
    void registerUser_존재하는_이메일() {
        // given
        UserDto duplicateUser = new UserDto(null, testUser.getEmail(), "사회학과", 3.5f, 2, 1);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(duplicateUser));
    }

    @Test
    @Transactional
    void registerUser_잘못된_이메일_도메인() {
        // given
        UserDto invalidEmailUser = new UserDto(null, "invalid@gmail.com", "사회학과", 3.5f, 2, 1);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(invalidEmailUser));
    }

    @Test
    @Transactional
    void updateUser() {
        // given
        UserDto updatedData = new UserDto(null, "test@ewha.ac.kr", "경영대학", 3.9f, 4, 1);

        // when
        UserDto updatedUser = userService.updateUser(userId, updatedData);

        // then
        assertThat(updatedUser.getId()).isEqualTo(userId);
        assertThat(updatedUser.getDepartment()).isEqualTo("경영대학");
        assertThat(updatedUser.getGpa()).isEqualTo(3.9f);
        assertThat(updatedUser.getYear()).isEqualTo(4);
        assertThat(updatedUser.getIncomeLevel()).isEqualTo(1);
    }

    @Test
    @Transactional
    void deleteUser() {
        // when
        userService.deleteUser(userId);

        // then
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }
}
