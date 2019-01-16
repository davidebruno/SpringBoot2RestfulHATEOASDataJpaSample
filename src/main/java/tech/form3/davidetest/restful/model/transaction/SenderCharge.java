
package tech.form3.davidetest.restful.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SenderCharge {

    @JsonProperty("amount")
    public String amount;
    @JsonProperty("currency")
    public String currency;

}
