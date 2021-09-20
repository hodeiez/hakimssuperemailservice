package hakimemailsender.presentation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailDto {
    private String name;
    private String sendTo;
    private String mailfrom;
    private String content;
    private String subject;
    private String type;

    public MailDto() {
    }
    @JsonCreator
    public MailDto(@JsonProperty("name")String name, @JsonProperty("email") String sendTo, @JsonProperty("sender") String mailfrom,
                   @JsonProperty("content") String content, @JsonProperty("subject") String subject, @JsonProperty("type") String type) {
        this.name = name;
        this.sendTo = sendTo;
        this.mailfrom = mailfrom;
        this.content = content;
        this.subject = subject;
        this.type = type;
    }


}
