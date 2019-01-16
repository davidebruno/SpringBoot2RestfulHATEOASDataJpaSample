
package tech.form3.davidetest.restful.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DebtorParty {

    @JsonProperty("account_name")
    public String accountName;
    @JsonProperty("account_number")
    public String accountNumber;
    @JsonProperty("account_number_code")
    public String accountNumberCode;
    @JsonProperty("address")
    public String address;
    @JsonProperty("bank_id")
    public String bankId;
    @JsonProperty("bank_id_code")
    public String bankIdCode;
    @JsonProperty("name")
    public String name;

}
