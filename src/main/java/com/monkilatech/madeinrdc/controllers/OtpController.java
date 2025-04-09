package com.monkilatech.madeinrdc.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/auth")
public class OtpController {

    @Value("${apikey-firebase}")
    private static String FIREBASE_API_KEY;
    
    private static final String SEND_OTP_URL = "https://identitytoolkit.googleapis.com/v1/accounts:sendVerificationCode?key=" + FIREBASE_API_KEY;
    private static final String VERIFY_OTP_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPhoneNumber?key=" + FIREBASE_API_KEY;

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam String phoneNumber) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> request = new HashMap<>();
        request.put("phoneNumber", phoneNumber);
        // request.put("recaptchaToken", "YOUR_RECAPTCHA_TOKEN");  // Optionnel

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity(SEND_OTP_URL, request, Map.class);
        return response.getBody().get("sessionInfo").toString(); 
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String sessionInfo, @RequestParam String otp) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> request = new HashMap<>();
        request.put("sessionInfo", sessionInfo);
        request.put("code", otp);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity(VERIFY_OTP_URL, request, Map.class);
        if (response.getBody().containsKey("idToken")) {
            return "Authentification réussie, Token: " + response.getBody().get("idToken");
        } else {
            return "Erreur d'authentification";
        }
    }
}
