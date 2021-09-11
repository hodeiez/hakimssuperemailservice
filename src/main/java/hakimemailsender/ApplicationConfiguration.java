package hakimemailsender;

import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.WelcomeMailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-08
 * Project: HakimEmailSender
 */
@Configuration
public class ApplicationConfiguration {

    @Value("${twilio.api.key}")
    private String apiKey;
    @Value("${twilio.email.from}")
    private String mailFrom;
    @Value("${template.id}")
    private String templateId;

    @Bean
    public EmailSender emailSender(){
        return new EmailSender(apiKey,mailFrom, templateId );
    }

    @Bean
    public WelcomeMailDto welcomeMailDto(){
        return new WelcomeMailDto();
    }
}
