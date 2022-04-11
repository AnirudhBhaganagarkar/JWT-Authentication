package com.cygnet.userservice.services;

import com.cygnet.userservice.repositorys.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      var user = userRepository.findUserByName(username);
      return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),new ArrayList<>());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
         return NoOpPasswordEncoder.getInstance();
    }
}
