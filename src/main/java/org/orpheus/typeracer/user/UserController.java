package org.orpheus.typeracer.user;

import lombok.AllArgsConstructor;
import org.orpheus.typeracer.user.DTO.AuthRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class UserController {

    final UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest user) {
        return userService.
    }

}
