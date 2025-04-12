package com.monkilatech.madeinrdc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.monkilatech.madeinrdc.payload.request.SendMessageRequest;
import com.monkilatech.madeinrdc.services.SmsService;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestBody SendMessageRequest messageRequest) {

        String messagePayload = 
                    "Bonjour, Vous avez demandé un code de vérification. \n Voici votre code :"
                     + messageRequest.getCode() +  "." ;


        smsService.sendSms(messageRequest.getTo(), messagePayload);
        return "SMS envoyé à " + messageRequest.getTo();
    }
}
