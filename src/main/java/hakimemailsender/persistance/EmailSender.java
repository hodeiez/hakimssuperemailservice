package hakimemailsender.persistance;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import hakimemailsender.domain.Emailer;
import hakimemailsender.presentation.WelcomeMailDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */

public class EmailSender implements Emailer {
        private String apiKey;
        private String mailFrom;
        private String templateId;

    public EmailSender(String apiKey, String mailFrom, String templateId) {
        this.apiKey = apiKey;
        this.mailFrom = mailFrom;
        this.templateId = templateId;

    }

    @Override
    public void sendWelcomeMail(WelcomeMailDto welcomeMailDto) throws IOException {
        Email from = new Email(mailFrom);
        Email to = new Email(welcomeMailDto.getSendTo());

        String subject = welcomeMailDto.getSubject();
        Content content = new Content("text/html", " ");

        Mail mail = new Mail(from, subject, to, content);

        mail.personalization.get(0).addDynamicTemplateData("first_name", welcomeMailDto.getName());
        mail.setTemplateId(templateId);


        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sg.api(request);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
        if(response.getStatusCode() >= 300){
        throw new IOException("sendgrid returned status code" + response.getStatusCode() +" "+ response.getBody());
        }

    }

}
