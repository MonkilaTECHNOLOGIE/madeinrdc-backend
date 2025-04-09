package com.monkilatech.madeinrdc.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    private static final Integer EXPIRE_MINS = 60;
    private LoadingCache<String, Integer> otpCache;

    public OtpService() {
        super();
        otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    public int generateOTP(String key) {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    public int getOtp(String key) {
        try {
            return otpCache.get(key);
        } catch (Exception e) {
            return 0;
        }
    }

    public void clearOTP(String key) {
        otpCache.invalidate(key);
    }

    // public String generateOtp() {
    //     int otp = 100000 + new java.util.Random().nextInt(900000);
    //     return String.valueOf(otp);
    // }

    public void sendOtp(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Votre code de vérification OTP");

            helper.setFrom("providermail30@gmail.com", "MadeInDRC");

            String body = "<p>Bonjour,</p>"
                    + "<p>Voici votre code de vérification :</p>"
                    + "<h2 style='color:blue;'>" + otp + "</h2>"
                    + "<p>Ce code est valide pendant 10 minutes.</p>"
                    + "<br><p><strong>MadeInDRC</strong></p>";

            helper.setText(body, true);

            mailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
