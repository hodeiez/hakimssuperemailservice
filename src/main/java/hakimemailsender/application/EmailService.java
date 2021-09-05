package hakimemailsender.application;

import hakimemailsender.domain.Emailer;
import hakimemailsender.presentation.WelcomeMailDto;
import lombok.AllArgsConstructor;
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

    private final Emailer emailer;

    public String sendWelcomeEmail (WelcomeMailDto welcomeMailDto) throws IOException {
        return emailer.sendWelcomeMail(welcomeMailDto);
    }
}
