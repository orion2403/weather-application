package com.orion.mapper;

import com.orion.dto.request.UserRequestDto;
import com.orion.dto.response.UserResponseDto;
import com.orion.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toUserFromUserDto(UserRequestDto userDto);

    UserResponseDto toUserResponseDtoFromUser(User user);
}
