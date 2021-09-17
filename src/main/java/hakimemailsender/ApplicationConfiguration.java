package hakimemailsender;

import hakimemailsender.persistance.EmailSender;
import hakimemailsender.persistance.Type;
import hakimemailsender.presentation.MailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-08
 * Project: HakimEmailSender
 */
@Configuration
@EnableWebMvc
public class ApplicationConfiguration {

    @Value("${twilio.api.key}")
    private String apiKey;
    @Value("${twilio.email.from}")
    private String mailFrom;
    @Value("${template.welcome.id}")
    private String templateIdWelcome;
    @Value("${template.confirmation.id}")
    private String templateIdConfirmation;
    private Type type;

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public EmailSender emailSender(){
        return new EmailSender(apiKey,mailFrom, templateIdWelcome, templateIdConfirmation, type);
    }

    @Bean
    public MailDto welcomeMailDto(){
        return new MailDto();
    }
}
