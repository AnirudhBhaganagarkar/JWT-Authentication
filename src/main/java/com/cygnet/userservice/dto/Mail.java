package com.cygnet.userservice.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail
{
    private String from;
    private String mailTo;
    private String subject;
    private String body;
}
