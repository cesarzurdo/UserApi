package org.demo.users.mapper;

import org.demo.users.model.Phone;
import org.demo.users.response.PhoneResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneToPhoneResponseMapper {

    PhoneResponse convertToResponse(Phone phone);

}
