package com.example.auth_service.controller;

import com.example.auth_service.dto.AuthRequestDTO;
import com.example.auth_service.dto.JwtResponseDTO;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          JwtTokenProvider tokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequestDTO requestDto) {
        if (userRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        User newUser = new User();
        newUser.setUsername(requestDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        newUser.setRoles(Collections.singleton("USER"));

        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody AuthRequestDTO requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getUsername(),
                        requestDto.getPassword()
                )
        );

        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}
