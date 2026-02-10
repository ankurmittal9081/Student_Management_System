package com.example.day3sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message =  "Email should be valid")

    private  String email;

    @NotBlank(message = "Password is requird")
    @Size(min=6 ,message = "Password must be atleat 6 characters")
    private  String password;
}
