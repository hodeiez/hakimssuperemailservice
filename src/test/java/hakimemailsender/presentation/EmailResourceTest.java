package hakimemailsender.presentation;

import com.github.tomakehurst.wiremock.WireMockServer;
import hakimemailsender.ApplicationConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Testcontainers
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


    @Test
    void sendMailSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:1234/welcome"))
                .andExpect(status().is2xxSuccessful())
                .andExpect((ResultMatcher) jsonPath("$.body", "welcome email sent"));
    }



}