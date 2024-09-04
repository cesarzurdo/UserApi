package org.demo.users.mapper;

import org.demo.users.model.User;
import org.demo.users.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserToUserResponseMapper {

    UserResponse convertToResponse(User user);

}
