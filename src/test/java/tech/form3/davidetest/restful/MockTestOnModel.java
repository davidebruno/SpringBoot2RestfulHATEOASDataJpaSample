package tech.form3.davidetest.restful;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tech.form3.davidetest.restful.api.PaymentRepository;
import tech.form3.davidetest.restful.model.transaction.Attributes;
import tech.form3.davidetest.restful.model.transaction.Payment;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.junit.Assert.assertThat;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import static org.mockito.Mockito.verify;

/**
 * Unit tests using Mock
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTestOnModel {

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void Should_Create_NewEmptyPayment() {
        // given
        Payment payment = new Payment();
        payment.setOrganizationId("organization id");
        payment.setAttributes(new Attributes());
        given(paymentRepository.save(any(Payment.class))).willAnswer(returnsFirstArg());

        // when
        Payment newPayment = createNewPayment(payment);

        // then
        verify(paymentRepository).save(newPayment);
        assertThat(newPayment.getOrganizationId(), equalTo("organization id"));
        assertThat(newPayment.getAttributes(), equalTo(payment.getAttributes()));
    }


    /**
     * NOTE: This and similar methods should to be contained in a Service class that manages
     * all the operations required for the Payment (Model)
     *
     * Creates an empty payment and save it on the DB
     *
     * @return the created payment
     */
    public Payment createNewPayment(Payment payment) {
        Payment newPayment = new Payment();
        newPayment.setOrganizationId(payment.getOrganizationId());
        newPayment.setAttributes(payment.getAttributes());
        return paymentRepository.save(newPayment);
    }
}