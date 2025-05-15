package com.narteykwamemeshack.store.mappers;

import com.narteykwamemeshack.store.dtos.RegisterUserRequest;
import com.narteykwamemeshack.store.dtos.UpdateUserRequest;
import com.narteykwamemeshack.store.dtos.UserDto;
import com.narteykwamemeshack.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);

}
