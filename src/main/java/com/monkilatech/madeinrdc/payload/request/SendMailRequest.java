package com.monkilatech.madeinrdc.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {
    private String phone;
    private String email;
    private String username;
    private int otpCode;
}
