package com.erisasadikllari.tbu_tiranabank.services;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import com.erisasadikllari.tbu_tiranabank.models.TransactionRequest;
import com.erisasadikllari.tbu_tiranabank.repositories.AccountRepository;
import com.erisasadikllari.tbu_tiranabank.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

//    @Transactional
//    public Transaction createTransaction(Transaction transaction, Long accountId){
//        Account debitAccount=accountRepository.findById(accountId).orElse(null);
//        Optional<Account> creditAccountOpt=accountRepository.findByAccountNumber(transaction.getCredit_account());
//        if (!creditAccountOpt.isPresent()){
//            throw new IllegalArgumentException("Credit Account Number does not exist!");
//        }
//        Account creditAccount=creditAccountOpt.get();
//
//        if (!debitAccount.getCurrency().equals(creditAccount.getCurrency())){
//            throw new IllegalArgumentException("Debit and Credit Account must have the same currency!");
//        }
//        double amount=transaction.getAmount();
//        if (debitAccount.getBalance()<amount){
//            throw new IllegalArgumentException("This account does not have sufficient balance!");
//        }
//
//        debitAccount.setBalance(debitAccount.getBalance()-amount);
//        creditAccount.setBalance(creditAccount.getBalance()+amount);
//
//        accountRepository.save(debitAccount);
//        accountRepository.save(creditAccount);
//
//        transaction.setDebitAccount(debitAccount);
//        transaction.setCredit_account(creditAccount.getAccountNumber());
//
//        return transactionRepository.save(transaction);
//    }

    @Transactional
    public void createTransaction(TransactionRequest transactionRequest) {
        // Get the debit account
        Account debitAccount = accountRepository.findById(transactionRequest.getDebitAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Debit account not found"));

        // Validate if the credit account exists and retrieve it
        Account creditAccount = accountRepository.findByAccountNumber(transactionRequest.getCreditAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Credit account not found"));

        // Check if debit account currency matches credit account currency
        if (debitAccount.getCurrency() != creditAccount.getCurrency()) {
            throw new IllegalArgumentException("Accounts must have the same currency for transaction");
        }

        // Check if debit account has enough balance
        if (debitAccount.getBalance() < transactionRequest.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance in debit account");
        }

        // Subtract amount from debit account
        debitAccount.setBalance(debitAccount.getBalance() - transactionRequest.getAmount());

        // Add amount to credit account
        creditAccount.setBalance(creditAccount.getBalance() + transactionRequest.getAmount());

        // Update accounts in database
        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);

        // Create transaction record
        Transaction transaction = new Transaction();
        transaction.setDebitAccount(debitAccount);
        transaction.setCredit_account(transactionRequest.getCreditAccountNumber());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setTransaction_date(new Date());

        transactionRepository.save(transaction);
    }
}
