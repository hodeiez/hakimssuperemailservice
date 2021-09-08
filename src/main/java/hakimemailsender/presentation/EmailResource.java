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
    private WelcomeMailDto welcomeMailDto;


    @GetMapping("/customer/add")
    public String getRecipient(@RequestBody RecipientDto recipientDto) throws IOException {
    return "redirect: /customer/mail/sendMail/" + recipientDto.getFirstName() + "/email/" + recipientDto.getEmail() ;
    }

    @PostMapping("customer/mail/sendMail/{firstName}/email/{email}")
    public ResponseEntity<?> sendMail(@PathVariable("email") String email, @PathVariable("firstName") String firstName) throws IOException {
        welcomeMailDto = new WelcomeMailDto(email, "ss@ss", "hello" + firstName, "hello" );
        emailService.sendWelcomeEmail(welcomeMailDto);
        return ResponseEntity.ok().body("email sent");
    }

}
