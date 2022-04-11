package com.cygnet.userservice;

import com.cygnet.userservice.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {SecurityConfigTest.class})
class SecurityConfigTest
{

    public WebSecurity webSecurity = null;


    @Test
    void AuthenticationManagerTest() throws Exception {
        SecurityConfig mock =  mock(SecurityConfig.class);
        mock.authenticationManagerBean();
        verify(mock).authenticationManagerBean();
    }

}


