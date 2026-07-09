package org.orpheus.typeracer.security.basic_layers;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.security.DTO.AuthRequest;
import org.orpheus.typeracer.security.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final JwtUtil jwtUtil;
    //private final PasswordEncoder passwordEncoder;

    public String register(AuthRequest request) {

        if(userRepository.existsByUsername((request.getUsername()))){
            throw new RuntimeException("Username already exists");
        }
    }
}
