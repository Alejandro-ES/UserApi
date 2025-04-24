package com.example.user.api.fakeModels;

import com.example.user.api.dto.PhoneRequest;
import com.example.user.api.dto.UserRequest;
import com.example.user.api.model.Phone;
import com.example.user.api.model.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class UserFake {

    public static UserRequest getUserRequestValid() {
        return UserRequest.builder()
                .name("Juan Rodriguez")
                .email("juan@rodriguez.org")
                .password("Hunter123@1")
                .phones(List.of(
                        PhoneRequest.builder()
                                .number("1234567")
                                .cityCode("1")
                                .contryCode("57")
                                .build(),
                        PhoneRequest.builder()
                                .number("7654321")
                                .cityCode("2")
                                .contryCode("54")
                                .build()
                ))
                .build();
    }

    public static User getUserValid() {
        UserRequest userRequest = getUserRequestValid();
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(List.of(
                        Phone.builder()
                                .number("1234567")
                                .citycode("1")
                                .contrycode("57")
                                .build()
                ))
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("asdasdasd")
                .active(true)
                .build();
    }
}
