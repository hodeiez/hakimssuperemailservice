package hakimemailsender.application;


import hakimemailsender.ApplicationConfiguration;
import hakimemailsender.persistance.EmailSender;
import hakimemailsender.presentation.MailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
    void sendEmailTestSuccess() throws IOException {

    MailDto mail = new MailDto("test", "testingprogramingthings@gmail.com", "testingprograminthings@gmail.com",
            "empty", "empty", "type");

    emailService.sendEmail(mail);
    verify(emailSender).sendMail(any());

    }

    @Test
    void sendEmailTestFailure() throws IOException {

        MailDto mail = new MailDto("test", "testingprogramingthings@gmail.com", "testingprograminthings@gmail.com",
                "empty", "empty", "type");

        doThrow(new IOException()).when(emailSender).sendMail(any(MailDto.class));
        assertThrows(ResponseStatusException.class,()->emailService.sendEmail(mail));

    }

}
