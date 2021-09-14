package hakimemailsender.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import hakimemailsender.ApplicationConfiguration;
import hakimemailsender.application.EmailService;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
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