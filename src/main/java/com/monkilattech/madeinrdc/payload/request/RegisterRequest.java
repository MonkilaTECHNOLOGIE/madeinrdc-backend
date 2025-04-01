package com.monkilattech.madeinrdc.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; 
    private String profil;
    private String phone;
    private Boolean status;
}
