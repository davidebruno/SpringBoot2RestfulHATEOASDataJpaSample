
package tech.form3.davidetest.restful.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attributes {

    @JsonProperty("amount")
    public BigDecimal amount;

    @JsonProperty("beneficiary_party")
    public BeneficiaryParty beneficiaryParty;

    @JsonProperty("charges_information")
    public ChargesInformation chargesInformation;

    @JsonProperty("currency")
    public Currency currency;

    @JsonProperty("debtor_party")
    public DebtorParty debtorParty;

    @JsonProperty("end_to_end_reference")
    public String endToEndReference;

    @JsonProperty("fx")
    public Fx fx;

    @JsonProperty("numeric_reference")
    public String numericReference;

    @JsonProperty("payment_id")
    public String paymentId;

    @JsonProperty("payment_purpose")
    public String paymentPurpose;

    @JsonProperty("payment_scheme")
    public String paymentScheme;

    @JsonProperty("payment_type")
    public String paymentType;

    @JsonProperty("processing_date")
    public LocalDate processingDate;

    @JsonProperty("reference")
    public String reference;

    @JsonProperty("scheme_payment_sub_type")
    public String schemePaymentSubType;

    @JsonProperty("scheme_payment_type")
    public String schemePaymentType;

    @JsonProperty("sponsor_party")
    public SponsorParty sponsorParty;

}
