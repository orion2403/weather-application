package com.orion.interceptor;

import com.orion.dto.response.SessionDto;
import com.orion.dto.response.UserResponseDto;
import com.orion.service.UserService;
import com.orion.service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SessionManagerInterceptor implements HandlerInterceptor {

    private static final String HOME_PAGE = "/weather/ap/";
    private static final String LOGIN_PAGE = "/weather/auth/login";
    private static final String ERROR_PAGE = "/weather/error/";

    private final SessionService sessionService;
    private final UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var sessionId = WebUtils.getCookie(request, "sessionId");

        if (sessionId == null) {
            return true;
        }

        try {
            var id = UUID.fromString(sessionId.getValue());
            var session = sessionService.findById(id);

            if (isSessionExpired(session)) {
                invalidateSession(response, session);
                return true;
            }

            response.sendRedirect(HOME_PAGE);
            return false;
        } catch (RuntimeException e) {
            response.sendRedirect(ERROR_PAGE);
        }
        return true;
    }

    private boolean isSessionExpired(SessionDto session) {
        return session.expiresAt().isBefore(LocalDateTime.now());
    }

    private void invalidateSession(HttpServletResponse response, SessionDto session) throws IOException {
        response.addCookie(new Cookie("session_id", ""));
        sessionService.delete(session);
        response.sendRedirect(LOGIN_PAGE);
    }
}
