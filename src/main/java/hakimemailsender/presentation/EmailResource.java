package hakimemailsender.presentation;

import hakimemailsender.application.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */
@RestController
@AllArgsConstructor
public class EmailResource {

    private final EmailService emailService;


    @PostMapping("/welcome")
    public ResponseEntity<?> sendWelcomeMail(@RequestBody MailDto mail) {
        emailService.sendEmail(mail);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/confirm")
    public ResponseEntity<?> sendConfirmationMail(@RequestBody MailDto mail) {
        emailService.sendEmail(mail);
        return ResponseEntity.ok().build();

    }
}
