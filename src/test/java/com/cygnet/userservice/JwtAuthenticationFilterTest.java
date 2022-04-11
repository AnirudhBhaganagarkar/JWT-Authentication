package com.cygnet.userservice;

import com.cygnet.userservice.config.JwtAuthenticationFilter;
import com.cygnet.userservice.helper.JwtUtil;
import com.cygnet.userservice.services.CustomUserDetailsService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {JwtAuthenticationFilterTest.class})
class JwtAuthenticationFilterTest
{
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    FilterChain filterChain;

    @Mock
    UserDetails userDetails;

    @Mock
    Authentication authentication;

    @Mock
    SecurityContext securityContext;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    CustomUserDetailsService customUserDetailsService;

    @Mock
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;


    @Test
    void doFilterInternalTest() throws ServletException, IOException {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSb21hbiIsImV4cCI6MTY0NTIwNTMxNSwiaWF0IjoxNjQ1MTY5MzE1fQ.MuJyE8rkDIX3bZH7xRRytsbQhbEwq6MRikXdEClzYtw";
        when(request.getHeader("Authorization")).thenReturn("Bearer" +token);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(jwtUtil.extractUsername(token)).thenReturn("Roman");
        assertEquals("Roman",jwtUtil.extractUsername(token));

        JwtAuthenticationFilter mockJwt = mock(JwtAuthenticationFilter.class);
        mockJwt.doFilterInternal(request,response,filterChain);


        verify(mockJwt,times(1)).doFilterInternal(request,response,filterChain);
        verifyNoMoreInteractions(mockJwt);
    }




}
