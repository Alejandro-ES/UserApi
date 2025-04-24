package com.example.user.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "El name no puede estar vacío")
    private String name;
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe tener un formato válido. Ej: aaaaaaa@dominio.com")
    private String email;
    @NotBlank(message = "La password no puede estar vacía")
    private String password;
    @Valid
    private List<PhoneRequest> phones;
}
