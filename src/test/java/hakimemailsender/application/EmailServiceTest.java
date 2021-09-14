package hakimemailsender.application;


import hakimemailsender.ApplicationConfiguration;
import hakimemailsender.application.EmailService;
import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.WelcomeMailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class EmailServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmailSender emailSender;

    EmailService emailService;


    @BeforeEach
    public void init(){
        emailService = new EmailService(emailSender);
    }

    @Test
    void sendEmailTest() throws IOException {

    WelcomeMailDto mail = new WelcomeMailDto("test", "testingprogramingthings@gmail.com", "testingprograminthings@gmail.com",
            "empty", "empty");
    when(emailSender.sendWelcomeMail(any(WelcomeMailDto.class))).thenReturn("202");
    String status = emailService.sendWelcomeEmail(mail);

    assertEquals("email sent", status);
    verify(emailSender).sendWelcomeMail(any());

    }

}
