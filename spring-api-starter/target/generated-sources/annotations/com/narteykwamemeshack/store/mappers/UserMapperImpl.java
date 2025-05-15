package com.narteykwamemeshack.store.mappers;

import com.narteykwamemeshack.store.dtos.RegisterUserRequest;
import com.narteykwamemeshack.store.dtos.UpdateUserRequest;
import com.narteykwamemeshack.store.dtos.UserDto;
import com.narteykwamemeshack.store.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-14T14:39:54+0000",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public User toEntity(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        return user;
    }

    @Override
    public void update(UpdateUserRequest request, User user) {
        if ( request == null ) {
            return;
        }
    }
}
