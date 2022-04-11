package com.cygnet.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest
{
    String name;
    String password;
}
