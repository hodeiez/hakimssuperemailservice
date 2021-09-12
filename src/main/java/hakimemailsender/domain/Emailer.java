package hakimemailsender.domain;

import hakimemailsender.presentation.WelcomeMailDto;

import java.io.IOException;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */

public interface Emailer {

   String sendWelcomeMail(WelcomeMailDto welcomeMailDto) throws IOException;

}
