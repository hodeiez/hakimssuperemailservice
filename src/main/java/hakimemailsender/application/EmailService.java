package hakimemailsender.application;

import hakimemailsender.domain.Emailer;
import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.WelcomeMailDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String sendWelcomeEmail (WelcomeMailDto welcomeMailDto) throws IOException {
        return emailSender.sendWelcomeMail(welcomeMailDto);
    }
}
