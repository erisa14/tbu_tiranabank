package com.erisasadikllari.tbu_tiranabank.repositories;

import com.erisasadikllari.tbu_tiranabank.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findByPersonalNr(String personal_nr);
}
