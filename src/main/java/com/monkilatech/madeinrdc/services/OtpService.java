package com.monkilatech.madeinrdc.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    public String generateOtp() {
        int otp = 100000 + new java.util.Random().nextInt(900000);
        return String.valueOf(otp);
    }

    public void sendOtp(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("🔐 Votre code de vérification OTP");

            
            helper.setFrom("providermail30@gmail.com", "MadeInDRC");

            
            String body = "<p>Bonjour,</p>"
                    + "<p>Voici votre code de vérification :</p>"
                    + "<h2 style='color:blue;'>" + otp + "</h2>"
                    + "<p>Ce code est valide pendant 10 minutes.</p>"
                    + "<br><p><strong>MonApp Sécurité</strong></p>";

            helper.setText(body, true); 

            mailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
