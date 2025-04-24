package com.example.user.api.mapper;

import com.example.user.api.dto.PhoneRequest;
import com.example.user.api.dto.UserRequest;
import com.example.user.api.dto.UserResponse;
import com.example.user.api.model.Phone;
import com.example.user.api.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    User toEntity(UserRequest request);

    Phone toEntity(PhoneRequest request);

    List<Phone> toEntity(List<PhoneRequest> phones);
}