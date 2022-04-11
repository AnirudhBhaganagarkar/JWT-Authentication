package com.cygnet.userservice;

import com.cygnet.userservice.helper.JwtUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import static org.mockito.Mockito.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {JwtUtilTest.class})
class JwtUtilTest
{
    @Mock
    UserDetails userDetails = null;

    @Test
    void extractUsernameTest() throws Exception
    {
        JwtUtil mock = mock(JwtUtil.class);
        mock.extractUsername("anirudh");
        verify(mock).extractUsername("anirudh");
        verifyNoMoreInteractions(mock);
    }

    @Test
    void extractExpirationTest() throws Exception
    {
        JwtUtil mock = mock(JwtUtil.class);
        mock.extractExpiration("tokan");
        verify(mock).extractExpiration("tokan");
        verifyNoMoreInteractions(mock);
    }

    @Test
    void generateTokenTest() throws Exception
    {
        JwtUtil mock = mock(JwtUtil.class);
        mock.generateToken(userDetails);
        verify(mock).generateToken(userDetails);
        verifyNoMoreInteractions(mock);
    }


    @Test
    void validateTokenTest() throws Exception
    {
        JwtUtil mock = mock(JwtUtil.class);
        mock.validateToken("token",userDetails);
        verify(mock).validateToken("token",userDetails);
        verifyNoMoreInteractions(mock);
    }
}
