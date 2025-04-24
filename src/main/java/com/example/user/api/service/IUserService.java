package com.example.user.api.service;

import com.example.user.api.dto.UserRequest;
import com.example.user.api.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User createUser(UserRequest userRequest);
}
