package hakimemailsender.application;

import hakimemailsender.domain.Emailer;
import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.WelcomeMailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */
@AllArgsConstructor
@Service
public class EmailService {

    private final EmailSender emailSender;

    public void sendWelcomeEmail (WelcomeMailDto welcomeMailDto) {
       try {
           emailSender.sendWelcomeMail(welcomeMailDto);
       } catch (IOException exception){
           throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
       }
    }
}
