package hakimemailsender.presentation;

import hakimemailsender.application.EmailService;
import hakimemailsender.domain.Emailer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

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
    public ResponseEntity<?> sendWelcome(@RequestBody WelcomeMailDto mail) {
        emailService.sendWelcomeEmail(mail);
        return ResponseEntity.ok().build();

    }
}
