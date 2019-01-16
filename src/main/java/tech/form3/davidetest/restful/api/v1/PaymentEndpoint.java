package tech.form3.davidetest.restful.api.v1;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.Optional;

import tech.form3.davidetest.restful.model.transaction.Payment;
import tech.form3.davidetest.restful.api.PaymentRepository;
import tech.form3.davidetest.restful.api.exceptions.PaymentNotFoundException;
import tech.form3.davidetest.restful.model.RestResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PaymentEndpoint {

	@Autowired
	private PaymentRepository paymentRepository;

	@GetMapping("/payments")
	public RestResource<Iterable<Payment>> retrieveAllStudents() {

		Iterable<Payment> studentsResource = paymentRepository.findAll();
		RestResource<Iterable<Payment>> paymentResource = new RestResource<>(studentsResource);
		paymentResource.add(linkTo(methodOn(PaymentEndpoint.class).retrieveAllStudents()).withSelfRel());

		return paymentResource;
	}

	@GetMapping("/payments/{id}")
	public Resource<Payment> retrieveStudent(@PathVariable String id) {
		Optional<Payment> payment = paymentRepository.findById(id);

		if (!payment.isPresent())
			throw new PaymentNotFoundException("The transaction with Id: " + id + " was NOT FOUND");

		Resource<Payment> resource = new Resource<Payment>(payment.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());

		resource.add(linkTo.withRel("payments"));

		return resource;
	}

	@DeleteMapping("/payments/{id}")
	public void deletePayment(@PathVariable String id) {
		paymentRepository.deleteById(id);
	}

	@PostMapping("/payments")
	public ResponseEntity<Object> createPayment(@RequestBody Payment payment) {
		Payment savedPayment = paymentRepository.save(payment);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPayment.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/payments/{id}")
	public ResponseEntity<Object> updatePayment(@RequestBody Payment payment, @PathVariable String id) {

		Optional<Payment> paymentOptional = paymentRepository.findById(id);

		if (!paymentOptional.isPresent())
			return ResponseEntity.notFound().build();

		paymentRepository.save(payment);

		return ResponseEntity.noContent().build();
	}
}
