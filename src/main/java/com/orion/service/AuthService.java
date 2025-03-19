package com.orion.service;

import com.orion.dto.SessionDto;
import com.orion.dto.UserLoginDto;
import com.orion.dto.UserRegisterDto;
import com.orion.exception.IncorrectPasswordEnteredException;
import com.orion.exception.UserAlreadyExistException;
import com.orion.exception.UserNotFoundException;
import com.orion.mapper.SessionMapper;
import com.orion.mapper.UserMapper;
import com.orion.model.Session;
import com.orion.model.User;
import com.orion.repository.SessionRepository;
import com.orion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private static final Long SESSION_LIFETIME = 24L;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final UserMapper userMapper;
    private final SessionMapper sessionMapper;

    public void register(UserRegisterDto userRegisterDto) {
        var user = userMapper.toUserFromRegisterDto(userRegisterDto);
        user.setPassword(hashPassword(user.getPassword()));

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistException("User with this login already exists");
        }

        userRepository.save(user);
    }


    public SessionDto login(UserLoginDto userLoginDto) {
        var user = userMapper.toUserFromUserLoginDto(userLoginDto);
        user.setPassword(hashPassword(user.getPassword()));
        var maybeUser = userRepository.findByLogin(user.getLogin());

        if (maybeUser.isEmpty()) {
            throw new UserNotFoundException("User with this login not found");
        }
        if (!BCrypt.checkpw(userLoginDto.password(), maybeUser.get().getPassword())) {
            throw new IncorrectPasswordEnteredException("You entered the wrong password");
        }

        var session = sessionRepository.save(createSession(maybeUser.get()));
        return sessionMapper.toSessionDtoFromSession(session);
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private Session createSession(User user) {
        return Session.builder()
                .id(UUID.randomUUID())
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(SESSION_LIFETIME))
                .build();
    }

}
