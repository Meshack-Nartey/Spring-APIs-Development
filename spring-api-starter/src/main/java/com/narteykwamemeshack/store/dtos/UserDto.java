package com.narteykwamemeshack.store.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.narteykwamemeshack.store.mappers.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @JsonProperty("user_id")
    private Long id;
    private String name;
    private String email;
}