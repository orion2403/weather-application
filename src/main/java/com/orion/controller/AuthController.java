package com.orion.controller;

import com.orion.dto.request.UserRequestDto;
import com.orion.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/weather/auth")
@RequiredArgsConstructor
public class AuthController {

    public static final String REGISTER_PAGE = "register";
    public static final String LOGIN_PAGE = "login";
    public static final String REDIRECT_TO_HOMEPAGE = "redirect:weather/ap/home";

    private final AuthService authService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRequestDto", UserRequestDto.builder().build());
        return REGISTER_PAGE;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userRequestDto", UserRequestDto.builder().build());
        return LOGIN_PAGE;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRequestDto userRequestDto,
                           BindingResult bindingResult,
                           HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return REGISTER_PAGE;
        }

        var registered = authService.register(userRequestDto);
        return REDIRECT_TO_HOMEPAGE;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userDto") @Valid UserRequestDto userDto,
                        BindingResult bindingResult,
                        HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return LOGIN_PAGE;
        }

        var loggedIn = authService.login(userDto);
        return REDIRECT_TO_HOMEPAGE;
    }
}
