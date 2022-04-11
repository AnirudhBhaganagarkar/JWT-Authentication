package com.cygnet.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto
{
    @JsonProperty("id")
    private long id;

    @NotBlank
    @JsonProperty("street")
    private String street;

    @NotBlank
    @JsonProperty("area")
    private String area;

    @JsonProperty("postalcode")
    private long postalcode;

    @NotBlank
    @JsonProperty("city")
    private String city;

    @NotBlank
    @JsonProperty("state")
    private String state;

    @NotBlank
    @JsonProperty("country")
    private String country;

}
