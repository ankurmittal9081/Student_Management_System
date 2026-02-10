package com.example.day3sms.service;

import com.example.day3sms.dto.LoginRequestDto;
import com.example.day3sms.dto.RegisterRequestDto;
import com.example.day3sms.dto.TokenResponseDto;
import com.example.day3sms.model.UserModel;
import com.example.day3sms.repository.UserRepository;
import com.example.day3sms.util.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository,
                       JwtUtil jwtUtil,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponseDto login(LoginRequestDto dto) {

        UserModel user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenResponseDto(token);
    }

    public TokenResponseDto register(RegisterRequestDto dto) {

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        UserModel newUser = new UserModel();
        newUser.setEmail(dto.getEmail());

        // 🔐 hash password
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(newUser);

        String token = jwtUtil.generateToken(newUser.getEmail());
        return new TokenResponseDto(token);
    }
}
