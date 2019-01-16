
package tech.form3.davidetest.restful.model.transaction;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargesInformation {

    @JsonProperty("bearer_code")
    public String bearerCode;
    @JsonProperty("sender_charges")
    public List<SenderCharge> senderCharges = null;
    @JsonProperty("receiver_charges_amount")
    public String receiverChargesAmount;
    @JsonProperty("receiver_charges_currency")
    public String receiverChargesCurrency;

}
