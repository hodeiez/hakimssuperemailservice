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

    @Autowired
    private final EmailService emailService;

    private WelcomeMailDto welcomeMailDto;

/*
    @GetMapping("/customer/add") We don't need this(?)
    public ResponseEntity<?>  getRecipient(@RequestBody RecipientDto recipientDto) throws IOException {
    return ResponseEntity.ok().body("mail" + recipientDto.getfirstName());

    }

 */

    @PostMapping("customer/mail/sendMail/{firstName}/email/{email}")
    public ResponseEntity<?> sendMail(@PathVariable("email") String email, @PathVariable("firstName") String firstName) throws IOException {
        welcomeMailDto = new WelcomeMailDto(email, "ss@ss", "hello " + firstName, "hello" );
        emailService.sendWelcomeEmail(welcomeMailDto);
        return ResponseEntity.ok().body("email sent");
    }

    @PostMapping("/welcome")
    public ResponseEntity<?> sendWellcome(@RequestBody WelcomeMailDto mail) {

        mail.setSubject("hakims livs");
        System.out.println(mail.getContent());
        try {
            emailService.sendWelcomeEmail(mail);
            return ResponseEntity.ok().body(mail);
        }
        catch(IOException e){
            return ResponseEntity.badRequest().body(mail);
        }

    }

}
