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

//We can remove this once HakimSuperServer adjusts Emailreq with name
    @PostMapping("customer/mail/sendMail/{first_name}/email/{email}")
    public ResponseEntity<?> sendMail(@PathVariable("email") String email, @PathVariable("first_name") String name) throws IOException {
        welcomeMailDto = new WelcomeMailDto(name, email, "ss@ss", "hello " + name, "hello" ); //this get overrided by the template
        emailService.sendWelcomeEmail(welcomeMailDto);
        return ResponseEntity.ok().body("email sent");
    }


    @PostMapping("/welcome")
    public ResponseEntity<?> sendWelcome(@RequestBody WelcomeMailDto mail) {

        try {
            return ResponseEntity.ok().body(emailService.sendWelcomeEmail(mail));
        }
        catch(IOException e){
            return ResponseEntity.badRequest().body(mail);
        }
    }

}
