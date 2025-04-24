package com.example.user.api.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PhoneRequest {
    @Size(min = 7, max = 12, message = "El number debe tener entre 7 y 12 dígitos")
    @Pattern(regexp = "\\d+", message = "El number debe contener solo dígitos")
    private String number;
    @Size(min = 1, max = 5, message = "El citycode debe tener entre 1 y 5 dígitos")
    @Pattern(regexp = "\\d+", message = "El citycode debe contener solo dígitos")
    private String cityCode;
    @Size(min = 1, max = 5, message = "El contrycode debe tener entre 1 y 5 dígitos")
    @Pattern(regexp = "\\d+", message = "El contrycode debe contener solo dígitos")
    private String contryCode;
}
