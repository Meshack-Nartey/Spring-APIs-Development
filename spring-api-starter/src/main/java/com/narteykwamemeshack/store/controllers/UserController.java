package com.narteykwamemeshack.store.controllers;

import com.narteykwamemeshack.store.dtos.ChangePasswordRequest;
import com.narteykwamemeshack.store.dtos.RegisterUserRequest;
import com.narteykwamemeshack.store.dtos.UpdateUserRequest;
import com.narteykwamemeshack.store.dtos.UserDto;
import com.narteykwamemeshack.store.entities.User;
import com.narteykwamemeshack.store.mappers.UserMapper;
import com.narteykwamemeshack.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
     // RETRIEVING A USER FROM THE DATABASE
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }
    // ADDING A USER TO THE DATABASE
    @PostMapping
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder) {
        var user = userMapper.toEntity(request);
       userRepository.save(user);

       var userDto = userMapper.toDto(user);
       var uri = uriBuilder.path("/users/{id}")
               .buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    //UPDATING A USER
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") long id,
            @RequestBody UpdateUserRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.update(request, user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
    //DELETING A USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
      @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
           @RequestBody ChangePasswordRequest request,
            @PathVariable(name = "id") long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if(!user.getPassword().equals(request.getOldPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}