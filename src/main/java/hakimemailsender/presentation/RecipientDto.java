package hakimemailsender.presentation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jacaranda Perez
 * Date: 2021-09-05
 * Project: HakimEmailSender
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipientDto {
   private final String firstName;
   private final String email;

   @JsonCreator
   public RecipientDto(@JsonProperty("firstname")String firstName, @JsonProperty("email") String email) {
      this.firstName = firstName;
      this.email = email;
   }
}
