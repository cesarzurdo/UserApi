package org.demo.users.mapper;

import org.demo.users.model.User;
import org.demo.users.request.UserRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PhoneRequestToPhoneMapper.class)
public interface UserRequestToUserMapper {

    @Mappings({
            @Mapping(target = "uuid",  ignore = true),
            @Mapping(target = "created",  ignore = true),
            @Mapping(target = "last_login",  ignore = true),
            @Mapping(target = "modified",  ignore = true),
            @Mapping(target = "token",  ignore = true),
            @Mapping(target = "active", ignore = true)
    })
    User convertRequestToEntity(UserRequest userRequest);

    @AfterMapping
    default void setTargetAttributes(@MappingTarget User.UserBuilder userBuilder){
        LocalDateTime now = LocalDateTime.now();

        userBuilder.created(now);
        userBuilder.modified(now);
        userBuilder.last_login(now);
        userBuilder.uuid(UUID.randomUUID().toString());
        userBuilder.active(true);
    }

}
