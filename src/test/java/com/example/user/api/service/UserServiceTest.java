package com.example.user.api.service;

import com.example.user.api.dto.UserRequest;
import com.example.user.api.exception.EmailAlreadyExistsException;
import com.example.user.api.exception.InvalidPasswordFormatException;
import com.example.user.api.fakeModels.UserFake;
import com.example.user.api.mapper.UserMapper;
import com.example.user.api.model.Phone;
import com.example.user.api.model.User;
import com.example.user.api.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(properties = {
        "JWT_SECRET=Z3Z2aEZydDQ0NTMwY2FkcXZpaUpKNzYxYnZkNDc="
})
class UserServiceTest {

    @MockBean
    private IUserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void createUser_whenUserIsNotInDataBase_thenCreateUserAndReturnUserResponse() {
        // dado
        UserRequest request = UserFake.getUserRequestValid();
        User userEntity = UserFake.getUserValid();
        userEntity.setEmail(request.getEmail());

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);

        User createdUser = userService.createUser(request);

        // entonces
        assertNotNull(createdUser);
        assertEquals(userEntity.getEmail(), createdUser.getEmail());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowException_whenEmailAlreadyExists() {
        // dado
        UserRequest request = UserFake.getUserRequestValid();
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // entonces
        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.createUser(request)
        );

        assertEquals("El correo ya se encuentra registrado.", exception.getMessage());
    }

    @Test
    void shouldThrowException_whenPasswordDoesNotMatchFormat() {
        // dado
        UserRequest request = UserFake.getUserRequestValid();
        request.setPassword("123");

        // entonces
        InvalidPasswordFormatException exception = assertThrows(
                InvalidPasswordFormatException.class,
                () -> userService.createUser(request)
        );

        assertEquals("La contraseña no cumple con el formato requerido, debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una letra minúscula, un número y un carácter especial.", exception.getMessage());
    }

    @Test
    void createUser_whenUserHasPhones_thenPhonesAreAssignedToUser() {
        // dado
        UserRequest request = UserFake.getUserRequestValid();
        User userEntity = UserFake.getUserValid();
        Phone phone = new Phone();
        phone.setNumber("123456789");
        phone.setUser(userEntity);
        userEntity.setPhones(Arrays.asList(phone));

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);

        userService.createUser(request);

        // entonces
        assertNotNull(userEntity.getPhones());
        assertEquals(1, userEntity.getPhones().size());
    }
}