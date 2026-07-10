package org.orpheus.typeracer.security.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.security.DTO.AuthRequest;
import org.orpheus.typeracer.security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //private final JwtUtil jwtUtil;

    public String register(AuthRequest request) {

        if(userRepository.existsByUsername((request.getUsername()))){
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        try {
            userRepository.save(user);
            return "Welcome " + user.getUsername();
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user");
        }

        }


    }

