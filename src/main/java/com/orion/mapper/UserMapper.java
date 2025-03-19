package com.orion.mapper;

import com.orion.dto.UserLoginDto;
import com.orion.dto.UserRegisterDto;
import com.orion.dto.UserResponseDto;
import com.orion.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUserFromUserLoginDto(UserLoginDto userLoginDto);

    @Mapping(target = "id", ignore = true)
    User toUserFromRegisterDto(UserRegisterDto userRegisterDto);

    UserResponseDto toUserResponseDtoFromUser(User user);
}
