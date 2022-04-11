package com.cygnet.userservice.services;

import com.cygnet.userservice.dto.UserDto;
import com.cygnet.userservice.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService
{
    public User addUser(UserDto userDto);

    public List<UserDto> getAllUsers();

    public UserDto findUserById(Long id);

    public UserDto findUserByName(String name);

    public UserDto findByEmail(String email);

}
