package com.cygnet.userservice.mapper;

import com.cygnet.userservice.dto.AddressDto;
import com.cygnet.userservice.dto.UserDto;
import com.cygnet.userservice.entity.Address;
import com.cygnet.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;


@Mapper(componentModel = "spring")
public interface UserMapper
{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userMmodelToDto(Optional<User> user);

    List<UserDto> userModelsToDtos(List<User> users);

    List<User> userDtosToModels(List<UserDto> userDtos);

    User userDtoToMobel(UserDto userDto);





 //   @Mapping(target = "user", ignore = true)
    AddressDto addressModelToDto(Address address);

    List<AddressDto> addressModelsToDtos(List<Address> addresses);

    @Mapping(target = "user", ignore = true)
    Address addressDtoToModel(AddressDto addressDto);

    UserDto userMmodelToDto(User user);
}
