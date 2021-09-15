package hakimemailsender.presentation;
import hakimemailsender.application.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class EmailResourceTest {

    @Captor
    private ArgumentCaptor<WelcomeMailDto> argumentCaptor;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService mockEmailService;

    @Test
    void sendMailSuccess() throws Exception {
        String test = "{\"name\":\"test\",\"email\":\"testingprogramingthings@gmail.com\",\"sender\":" +
                "\"nothing\",\"content\":\"nothing\",\"subject\":\"nothing\"}";


    mockMvc.perform(MockMvcRequestBuilders.post("/welcome")
                .content(test)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    verify(mockEmailService).sendWelcomeEmail(argumentCaptor.capture());
    WelcomeMailDto welcomeMail = argumentCaptor.getValue();
    assertEquals("test", welcomeMail.getName());
    assertEquals("testingprogramingthings@gmail.com", welcomeMail.getSendTo());
    }

    @Test
    void sendMailFailure() throws Exception {

        doThrow(new ResponseStatusException(HttpStatus.BAD_GATEWAY)).when(mockEmailService).sendWelcomeEmail(any());

        String test = "{\"name\":\"test\",\"email\":\"testingprogramingthings@gmail.com\",\"sender\":" +
                "\"nothing\",\"content\":\"nothing\",\"subject\":\"nothing\"}";


        mockMvc.perform(MockMvcRequestBuilders.post("/welcome")
                        .content(test)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadGateway());

    }

}