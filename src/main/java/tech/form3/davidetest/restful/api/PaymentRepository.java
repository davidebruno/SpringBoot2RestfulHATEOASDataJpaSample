package tech.form3.davidetest.restful.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.form3.davidetest.restful.model.transaction.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {

    /**
     * Here we can add custom methods that may be needed
     */
}

