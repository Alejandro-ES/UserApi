package com.example.user.api.controller;

import com.example.user.api.dto.UserRequest;
import com.example.user.api.dto.UserResponse;
import com.example.user.api.mapper.UserMapper;
import com.example.user.api.model.User;
import com.example.user.api.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Operaciones relacionadas con usuarios")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Crear nuevo usuario", description = "Registra un nuevo usuario con email y contraseña válidos")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        User createdUser = userService.createUser(userRequest);
        UserResponse userResponse = userMapper.toResponse(createdUser);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
