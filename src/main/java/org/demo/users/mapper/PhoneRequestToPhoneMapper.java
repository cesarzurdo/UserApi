package org.demo.users.mapper;

import org.demo.users.model.Phone;
import org.demo.users.request.PhoneRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneRequestToPhoneMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true)
    })
    Phone convertRequestToEntity(PhoneRequest phoneRequest);

    List<Phone> convertRequestToEntityList(List<PhoneRequest> phoneRequests);

}
