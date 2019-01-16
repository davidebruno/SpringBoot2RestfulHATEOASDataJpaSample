
package tech.form3.davidetest.restful.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fx {

    @JsonProperty("contract_reference")
    public String contractReference;
    @JsonProperty("exchange_rate")
    public String exchangeRate;
    @JsonProperty("original_amount")
    public String originalAmount;
    @JsonProperty("original_currency")
    public String originalCurrency;

}
