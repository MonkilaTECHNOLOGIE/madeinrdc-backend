import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.PhoneAuthProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class OtpController {

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam String phoneNumber) {
        try {
            String verificationId = PhoneAuthProvider.verifyPhoneNumber(phoneNumber);
            return "OTP envoyé, ID: " + verificationId;
        } catch (Exception e) {
            return "Erreur lors de l’envoi de l’OTP: " + e.getMessage();
        }
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String otp, @RequestParam String verificationId) {
        try {
            String uid = FirebaseAuth.getInstance().verifyIdToken(otp).getUid();
            return "Authentification réussie, UID: " + uid;
        } catch (FirebaseAuthException e) {
            return "Erreur de vérification: " + e.getMessage();
        }
    }
}
