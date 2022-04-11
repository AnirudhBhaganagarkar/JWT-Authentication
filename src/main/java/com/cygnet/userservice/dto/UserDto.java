package com.cygnet.userservice.dto;


import com.cygnet.userservice.validators.UniqueUsername;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    @JsonProperty("id")
    private long id;

    @NotBlank
    @UniqueUsername
    private String name;

    @NotBlank
//    @UniqueMailID
    private String email;

    @NotBlank
    @Size(min=6,max=15)
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{6,15}$",message = "It contains at least 6 characters and at most 15 characters & one digit & one upper & lower case alphabet & one special character which includes !@#$%&*()-+=^ & It doesnot contain any white space")
    @JsonProperty("password")
    private String password;

    @NotBlank
    @Size(min=0,max=10, message = "Mobile Number must have 10 number")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile Number must have 10 number")
    @JsonProperty("mobile")
    private String mobile;


    @JsonProperty("address")
    @Valid
    private AddressDto address;
}
