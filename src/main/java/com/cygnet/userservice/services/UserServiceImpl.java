package com.cygnet.userservice.services;

import com.cygnet.userservice.dto.UserDto;
import com.cygnet.userservice.entity.User;
import com.cygnet.userservice.mapper.UserMapper;
import com.cygnet.userservice.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final EmailSenderService emailSenderService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(EmailSenderService emailSenderService, UserRepository userRepository, UserMapper userMapper) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User addUser(UserDto userDto)
    {
        return userRepository.save(userMapper.userDtoToMobel(userDto));
    }


    @Override
    public List<UserDto> getAllUsers()
    {
        return userMapper.userModelsToDtos(userRepository.findAll());
    }


    @Override
    public UserDto findUserById(Long id) {
        Optional<User> findData = userRepository.findById(id);
        if (findData.isPresent()) {
            return userMapper.userMmodelToDto(Optional.of(findData.get()));
        }
        return userMapper.userMmodelToDto((User) null);
    }

    @Override
    public UserDto findUserByName(String name) {
        Optional<User> findData = userRepository.findByName(name);
        if (findData.isPresent())
        {
            return userMapper.userMmodelToDto(Optional.of(findData.get()));
        }
        return userMapper.userMmodelToDto((User) null);
    }

    @Override
    public UserDto findByEmail(String email)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
        {
/*
            String pas = user.get().getPassword();

            String resetPasswordURL = "Your password Is :- "+ pas;
            emailSenderService.sendEmail("myonlinewallet01@gmail.com", "resetPasswordURL:- " + resetPasswordURL, "For testing purpose");
*/
            return userMapper.userMmodelToDto(Optional.of(user.get()));
        }
        return userMapper.userMmodelToDto((User) null);

    }


}