package io.github.alberes.guestdb.controller.mappers;

import io.github.alberes.guestdb.controller.dto.GuestDto;
import io.github.alberes.guestdb.domains.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    @Mapping(source = "legalEntityNumber", target = "legalEntityNumber")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "birthday", target = "birthday")
    Guest toEntity(GuestDto dto);

    @Mapping(source = "legalEntityNumber", target = "legalEntityNumber")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "birthday", target = "birthday")
    GuestDto toDTO(Guest guest);
}
