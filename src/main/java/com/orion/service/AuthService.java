package com.orion.service;

import com.orion.dto.request.UserRequestDto;
import com.orion.dto.response.UserResponseDto;
import com.orion.exception.IncorrectPasswordEnteredException;
import com.orion.exception.UserAlreadyExistException;
import com.orion.exception.UserNotFoundException;
import com.orion.mapper.UserMapper;
import com.orion.model.User;
import com.orion.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDto register(UserRequestDto userRequestDto) {
        var user = userMapper.toUserFromUserDto(userRequestDto);
        user.setPassword(hashPassword(user.getPassword()));

        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistException("User with login: " + user.getLogin() + " already exists");
        }

        var savedUser = userRepository.save(user);
        return userMapper.toUserResponseDtoFromUser(savedUser);
    }

    @Transactional
    public UserResponseDto login(UserRequestDto userRequestDto) {
        var user = userMapper.toUserFromUserDto(userRequestDto);
        user.setPassword(hashPassword(user.getPassword()));

        var maybeUser = userRepository.findByLogin(user.getLogin());

        if (maybeUser.isEmpty()) {
            throw new UserNotFoundException("User with this login not found");
        }
        if (!matches(userRequestDto.getPassword(), maybeUser.get().getPassword())) {
            throw new IncorrectPasswordEnteredException("You entered the wrong password");
        }

        return userMapper.toUserResponseDtoFromUser(maybeUser.get());
    }

    public UserResponseDto findById(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        maybeUser.orElseThrow(() -> new UserNotFoundException("User with this id not found"));
        return userMapper.toUserResponseDtoFromUser(maybeUser.get());
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean matches(String enteredPassword, String passwordFromDatabase) {
        return BCrypt.checkpw(enteredPassword, passwordFromDatabase);
    }
}
