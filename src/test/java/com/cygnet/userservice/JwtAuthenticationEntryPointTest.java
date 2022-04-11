package com.cygnet.userservice;

import com.cygnet.userservice.config.JwtAuthenticationEntryPoint;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {JwtAuthenticationEntryPointTest.class})
class JwtAuthenticationEntryPointTest
{
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authenticationException;

    @InjectMocks
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Test
    void commenceTest() throws Exception
    {
        JwtAuthenticationEntryPoint mock = mock(JwtAuthenticationEntryPoint.class);
        mock.commence(request,response,authenticationException);

        assertEquals(401,HttpServletResponse.SC_UNAUTHORIZED,"Unaurthorized");
        verify(mock,times(1)).commence(request,response,authenticationException);
    }

}



