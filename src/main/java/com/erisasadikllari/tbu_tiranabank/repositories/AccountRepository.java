package com.erisasadikllari.tbu_tiranabank.repositories;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();
    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findAllByCustomer_Id(Long customerId);
}
