package hakimemailsender.presentation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeMailDto {
    private final String sendTo;
    private final String mailfrom;
    private final String content;
    private final String subject;

    @JsonCreator
    public WelcomeMailDto(@JsonProperty("email") String sendTo, @JsonProperty("sender") String mailfrom,
                          @JsonProperty("content") String content, @JsonProperty("subject") String subject) {
        this.sendTo = sendTo;
        this.mailfrom = mailfrom;
        this.content = content;
        this.subject = subject;
    }


}
