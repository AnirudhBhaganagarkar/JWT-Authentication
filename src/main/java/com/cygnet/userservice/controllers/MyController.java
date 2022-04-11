package com.cygnet.userservice.controllers;

import com.cygnet.userservice.dto.ForgotPassword;
import com.cygnet.userservice.dto.ResponseDto;
import com.cygnet.userservice.dto.UserDto;
import com.cygnet.userservice.helper.Messages;
import com.cygnet.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MyController
{

    private final UserService userService;

    public MyController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/adduser")
    public ResponseEntity<Object> save(@RequestBody UserDto userDto)
    {
        return new ResponseEntity<>(userService.addUser(userDto),HttpStatus.CREATED);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserDto>> findAllUser()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }


    @GetMapping("/userbyid/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }


    @GetMapping("/userbyname/{name}")
    public ResponseEntity<ResponseDto<Object>> getByName(@PathVariable String name)
    {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .responseJson(userService.findUserByName(name))
                        .code(HttpStatus.OK.toString())
                        .message(Messages.USER_DETAILS)
                        .build(),
                HttpStatus.OK
        );

    }


    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto<Object>> forgotPassword(@Valid @RequestBody ForgotPassword forgotPassword)
    {
        return new ResponseEntity<>(
                ResponseDto.builder()
                        .responseJson(userService.findByEmail(forgotPassword.getEmail()))
                        .code(HttpStatus.OK.toString())
                        .message(Messages.MAIL_SENT_SUCCESSFULLY)
                        .build(),
                HttpStatus.OK
        );
    }


}
