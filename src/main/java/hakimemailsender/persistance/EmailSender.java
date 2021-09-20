package hakimemailsender.persistance;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import hakimemailsender.domain.Emailer;
import hakimemailsender.presentation.MailDto;

import java.io.IOException;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */

public class EmailSender implements Emailer {
        private final String apiKey;
        private final String mailFrom;
        private final String templateIdWelcome;
        private final String templateIdConfirmation;
        private Type type;

    public EmailSender(String apiKey, String mailFrom, String templateIdWelcome, String templateIdConfirmation, Type type) {
        this.apiKey = apiKey;
        this.mailFrom = mailFrom;
        this.templateIdWelcome = templateIdWelcome;
        this.templateIdConfirmation = templateIdConfirmation;
        this.type = type;

    }

    @Override
    public void sendMail(MailDto mailDto) throws IOException {
        Email from = new Email(mailFrom);
        Email to = new Email(mailDto.getSendTo());

        String subject = mailDto.getSubject();
        Content content = new Content("text/html", " ");

        Mail mail = new Mail(from, subject, to, content);

        mail.personalization.get(0).addDynamicTemplateData("first_name", mailDto.getName());
        String type = mailDto.getType();
        if(type.equals(Type.CONFIRM.toString())) {
            mail.setTemplateId(templateIdConfirmation);
        } else {
            mail.setTemplateId(templateIdWelcome);
        }

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
