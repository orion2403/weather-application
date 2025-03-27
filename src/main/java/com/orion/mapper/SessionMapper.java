package com.orion.mapper;

import com.orion.dto.response.SessionDto;
import com.orion.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SessionMapper {

    SessionDto toSessionDtoFromSession(Session session);

    Session toSessionFromSessionDto(SessionDto sessionDto);
}
