package com.cygnet.userservice;

import com.cygnet.userservice.services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CustomUserDetailsServiceTest.class})
class CustomUserDetailsServiceTest
{
    @Test
    void UserDetailsTest()
    {
        CustomUserDetailsService mock = mock(CustomUserDetailsService.class);
        mock.loadUserByUsername("anirudh");
        verify(mock).loadUserByUsername("anirudh");
    }

    @Test
    void PasswordEncodertest()
    {
        CustomUserDetailsService mock = mock(CustomUserDetailsService.class);
        mock.passwordEncoder();
        verify(mock).passwordEncoder();
    }
}
