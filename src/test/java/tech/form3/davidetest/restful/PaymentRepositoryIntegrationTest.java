package tech.form3.davidetest.restful;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tech.form3.davidetest.restful.api.PaymentRepository;
import tech.form3.davidetest.restful.api.exceptions.PaymentNotFoundException;
import tech.form3.davidetest.restful.model.transaction.Payment;
import tech.form3.davidetest.restful.model.transaction.BeneficiaryParty;
import tech.form3.davidetest.restful.model.transaction.Attributes;


import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * Integration tests
 */
@RunWith(SpringRunner.class)
@DataJpaTest
//@ContextConfiguration(classes = {Form3InfrastructureConfig.class, InfrastructureTestConfig.class})
@Transactional
public class PaymentRepositoryIntegrationTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void Should_Save_Payment_OnDB() {

        // given
        BigDecimal amount = new BigDecimal("8888.93");
        Payment payment = new Payment();
        Attributes paymentAttributes = new Attributes();
        paymentAttributes.setAmount(amount);
        BeneficiaryParty beneficiaryParty = new BeneficiaryParty();
        String accountName = "name of the account";
        beneficiaryParty.setAccountName(accountName);
        String endToEndRef = "Wil piano Jan";
        paymentAttributes.setEndToEndReference(endToEndRef);
        paymentAttributes.setBeneficiaryParty(beneficiaryParty);
        payment.setAttributes(paymentAttributes);

        // when
        Payment savedPayment = paymentRepository.save(payment);

        // then
        assertThat(savedPayment.getId(), equalTo(payment.getId()));
        assertThat(savedPayment.getAttributes().getAmount(), equalTo(amount));
        assertThat(savedPayment.getAttributes().getEndToEndReference(), equalTo(endToEndRef));
        assertThat(savedPayment.getAttributes().getBeneficiaryParty().getAccountName(), equalTo(accountName));
    }

    @Test
    public void Should_Get_Payment_FromDB() {

        // given
        Payment payment = createAndSavePayment();

        // when
        Optional<Payment> paymentFromDb = paymentRepository.findById(payment.getId());

        if (paymentFromDb.isPresent()) {
            // then
            assertThat(paymentFromDb.get(), equalTo(payment));
        } else {
            fail("No data was returned from a get request, TEST FAILED");
        }

    }

    @Test(expected = PaymentNotFoundException.class)
    public void Should_FailAndReturn_PaymentNotFoundException() {

        // given
        String notExistingId = "fake id transaction";

        // when
        Optional<Payment> result = paymentRepository.findById(notExistingId);

        if (! result.isPresent()){
            // Then
            throw new PaymentNotFoundException("Transaction Id not found");
        }
    }

    @Test
    public void Should_Update_Payment() {

        // given
        Payment payment = createAndSavePayment();
        String newType = "Credit updated";
        payment.getAttributes().setPaymentType(newType);

        // when
        Payment paymentFromDb = paymentRepository.save(payment);

        // then
        assertThat(paymentFromDb.getAttributes().getPaymentType(), equalTo(newType));
    }

    @Test
    public void Should_Delete_Payment() {

        // given
        Payment payment = createAndSavePayment();

        // when
        paymentRepository.deleteById(payment.getId());

        // then
        assertThat(paymentRepository.existsById(payment.getId()), equalTo(false));
    }

    private Payment createAndSavePayment() {
        Payment payment = new Payment();
        Attributes paymentAttributes = new Attributes();
        String type = "Credit";
        BigDecimal amount = new BigDecimal("888.975");
        paymentAttributes.setPaymentType(type);
        paymentAttributes.setAmount(amount);
        payment.setAttributes(paymentAttributes);
        return paymentRepository.save(payment);
    }
}
