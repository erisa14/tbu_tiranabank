package com.erisasadikllari.tbu_tiranabank.repositories;

import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
//    List<Transaction> findAllByAccount_Account_number();
}
