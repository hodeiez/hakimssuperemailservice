package hakimemailsender.presentation;

import hakimemailsender.application.EmailService;
import hakimemailsender.domain.Emailer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

/*
    @PostMapping("/customer/add")
    public ResponseEntity<?> sendEmail(@RequestBody RecipientDto recipientDto) throws IOException {
        WelcomeMailDto welcomeMailDto = new WelcomeMailDto(recipientDto.getEmail(), //from, content, subject

        //redirect to send email(takes WelcomeMailDto)
        //send email





    }
*/
}
