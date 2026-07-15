package org.orpheus.typeracer.security.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.security.DTO.AuthRequest;
import org.orpheus.typeracer.security.filter.JwtUtil;
import org.orpheus.typeracer.security.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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
            return "Welcome " + user.getUsername() + " login to continue";
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user");
        }

    }


    public String login(AuthRequest request){

        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isEmpty()) {
            throw new RuntimeException("Username not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.get().getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.get().getUsername());
    }



}

