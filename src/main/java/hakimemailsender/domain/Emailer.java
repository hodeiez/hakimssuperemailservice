package hakimemailsender.domain;

import hakimemailsender.presentation.MailDto;

import java.io.IOException;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */

public interface Emailer {

  void sendMail(MailDto mailDto) throws IOException;

}
