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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import java.io.IOException;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationConfiguration.class)
class EmailResourceTest {


    @Autowired
    private MockMvc mockMvc;

    private final static WireMockServer wireMockServer = new WireMockServer(1234);

    @BeforeEach
    public void before() {
        wireMockServer.start();
    }


    @AfterEach
    public void after() {
        wireMockServer.stop();
    }

//  new WelcomeMailDto("test","testingprogramingthings@gmail.com","testingprogramingthings@gmail.com", "nothing","nothing")

    @Test
    void sendMailSuccess() throws Exception {
    String test = "{\"name\":,\"test\",\"email\":\"testingprogramingthings@gmail.com\",\"sender\":" +
                "\"nothing\",\"content\":\"nothing\",\"subject\":\"nothing\"}";
        System.out.println(wireMockServer.isRunning());

    ResultActions resultActions = mockMvc.perform(post("/welcome")
                .content(test)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(MockMvcResultMatchers.status().isAccepted());
    }



/*
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

*/

/*
    @Test
    void sendMailSuccess(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(APPLICATION_JSON));

        HttpEntity<WelcomeMailDto> entity = new HttpEntity<>(new WelcomeMailDto("test","testingprogramingthings@gmail.com",
                "testingprogramingthings@gmail.com", "nothing","nothing"), headers);

        final ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:8082/welcome", entity, Void.class);
        System.out.println(response.getStatusCodeValue());

    } */
}