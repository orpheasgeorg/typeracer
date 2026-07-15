package org.orpheus.typeracer.security.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.security.DTO.AuthRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        return userService.login(request);
    }

}
