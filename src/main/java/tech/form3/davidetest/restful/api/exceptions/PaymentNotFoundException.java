package tech.form3.davidetest.restful.api.exceptions;

public class PaymentNotFoundException extends RuntimeException {

	public PaymentNotFoundException(String exception) {
		super(exception);
	}

}
