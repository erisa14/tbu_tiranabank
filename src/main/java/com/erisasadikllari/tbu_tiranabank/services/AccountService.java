package com.erisasadikllari.tbu_tiranabank.services;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.Customer;
import com.erisasadikllari.tbu_tiranabank.repositories.AccountRepository;
import com.erisasadikllari.tbu_tiranabank.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Account> getAllAccountsByCustomerId(Long customerId){
        return accountRepository.findAllByCustomer_Id(customerId);
    }

    public Account createAccount(Account account, Long customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        account.setCustomer(customer.get());
        account.setAccountNumber(generateAccountNumber());
        return accountRepository.save(account);
    }

    private String generateAccountNumber(){
        Random random=new Random();
        StringBuilder accountNumber=new StringBuilder();
        for (int i=0; i<12;i++){
            int digit=random.nextInt(10);
            accountNumber.append(digit);
        }
        return accountNumber.toString();
    }

    public Account getById(Long id){
        Optional<Account> account=accountRepository.findById(id);
        return account.orElse(null);
    }
}