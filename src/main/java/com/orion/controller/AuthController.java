package com.orion.controller;

import com.orion.dto.SessionDto;
import com.orion.dto.UserLoginDto;
import com.orion.dto.UserRegisterDto;
import com.orion.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/weather/auth")
@RequiredArgsConstructor
public class AuthController {

    public static final String REGISTER = "register";
    public static final String LOGIN = "login";
    private final AuthService authService;


    @GetMapping("/register")
    public String showRegisterForm(@ModelAttribute UserRegisterDto userRegisterDto) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserRegisterDto userRegisterDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return REGISTER;
        }
        authService.register(userRegisterDto);
        return "redirect:/weather/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute UserLoginDto userLoginDto) {
        return LOGIN;
    }

    @PostMapping("/login")
    public String login(@CookieValue(value = "session_id", defaultValue = "") String cookie,
                        @ModelAttribute @Valid UserLoginDto userLoginDto,
                        BindingResult bindingResult,
                        HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return LOGIN;
        }
        var sessionDto = authService.login(userLoginDto);
        createCookie(sessionDto, response);
        return "home";
    }

    private void createCookie(SessionDto sessionDto, HttpServletResponse response) {
        var cookie = new Cookie("session_id", sessionDto.id());
        cookie.setAttribute("expires_at", sessionDto.expiresAt());
        response.addCookie(cookie);
    }
}
