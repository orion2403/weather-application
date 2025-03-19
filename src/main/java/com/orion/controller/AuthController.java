package com.orion.controller;

import com.orion.dto.UserLoginDto;
import com.orion.dto.UserRegisterDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/weather/ap/auth")
public class AuthController {

    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    private final AuthService authService;


    @GetMapping("/register")
    public String showRegisterForm(@ModelAttribute UserRegisterDto userDto) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserRegisterDto userDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        // TODO add service for register user
        return null;
    }

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute UserLoginDto userDto) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid UserLoginDto userDto,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        // TODO add service for login user
        return null;
    }
}
