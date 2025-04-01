package com.monkilattech.madeinrdc.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailRequest {
    private String phone;
    private String email;
    private int otpCode;
}
