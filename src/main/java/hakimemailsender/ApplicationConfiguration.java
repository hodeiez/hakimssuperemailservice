package hakimemailsender;

import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.WelcomeMailDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-08
 * Project: HakimEmailSender
 */
@Configuration
public class ApplicationConfiguration {


    @Bean
    public EmailSender emailSender(){
        return new EmailSender();
    }

    @Bean
    public WelcomeMailDto welcomeMailDto(){
        return new WelcomeMailDto();
    }
}
