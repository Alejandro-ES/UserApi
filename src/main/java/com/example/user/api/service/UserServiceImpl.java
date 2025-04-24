package com.example.user.api.service;

import com.example.user.api.config.JwtUtil;
import com.example.user.api.dto.UserRequest;
import com.example.user.api.exception.EmailAlreadyExistsException;
import com.example.user.api.exception.InvalidPasswordFormatException;
import com.example.user.api.mapper.UserMapper;
import com.example.user.api.model.User;
import com.example.user.api.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Value("${password.regex}")
    private String passwordRegex;

    @Value("${password.message}")
    private String passwordMessage;

    @Override
    public User createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new EmailAlreadyExistsException("El correo ya se encuentra registrado.");
        }

        if (!passwordMatchesRegex(userRequest.getPassword(), passwordRegex)) {
            throw new InvalidPasswordFormatException(passwordMessage);
        }

        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(jwtUtil.generateToken(user.getEmail()));
        user.setActive(true);

        user.getPhones().forEach(phone -> phone.setUser(user));

        return userRepository.save(user);
    }

    private boolean passwordMatchesRegex(String password, String passwordRegex) {
        return password.matches(passwordRegex);
    }
}
