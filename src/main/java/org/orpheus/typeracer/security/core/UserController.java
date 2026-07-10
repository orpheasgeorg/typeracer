package org.orpheus.typeracer.security.core;

import lombok.RequiredArgsConstructor;
import org.orpheus.typeracer.security.DTO.AuthRequest;
import org.orpheus.typeracer.text.core.TextService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final TextService textService;

    @PostMapping("/register")
    public void register(@RequestBody AuthRequest request) {
        // return userService.register(request);
    }


    @GetMapping("/test-text")
    public String testText() {
        textService.getRandomText();
        return "check console";
    }


}
