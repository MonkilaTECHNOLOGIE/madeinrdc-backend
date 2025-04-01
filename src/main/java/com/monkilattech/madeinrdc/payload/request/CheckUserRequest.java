package com.monkilattech.madeinrdc.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckUserRequest {
    private String username;
    private String phone;
    private String email;
}
