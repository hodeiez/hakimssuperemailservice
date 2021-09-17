package hakimemailsender.application;

import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.MailDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public void sendEmail(MailDto mailDto) {
       try {
           emailSender.sendMail(mailDto);
       } catch (IOException exception){
           throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
       }
    }
}
