package com.cygnet.userservice.controllers;

import com.cygnet.userservice.dto.JwtRequest;
import com.cygnet.userservice.dto.JwtResponse;
import com.cygnet.userservice.helper.JwtUtil;
import com.cygnet.userservice.services.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController
{
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public JwtController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
    {

        try
        {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(),jwtRequest.getPassword()));
        }
        catch (UsernameNotFoundException | BadCredentialsException e)
        {
            throw new Exception("Bad Creadentials");
        }

        var userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getName());

        String token =  this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
