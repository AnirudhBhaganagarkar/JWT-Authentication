package com.cygnet.userservice.dto;

import lombok.*;


@Getter
@AllArgsConstructor
@Builder
public class ResponseDto<T>
{
    private String code;

    private String message;

    private T responseJson;

}
