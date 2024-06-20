package com.erisasadikllari.tbu_tiranabank.services;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.AccountType;
import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import com.erisasadikllari.tbu_tiranabank.repositories.AccountRepository;
import com.erisasadikllari.tbu_tiranabank.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    public Transaction createTransaction(Transaction transaction){
        Optional<Account> account=accountRepository.findById(transaction.getDebitAccount().getId());
        Optional<Account> creditAccountOpt=accountRepository.findByAccountNumber(transaction.getCreditAccount());
        if (creditAccountOpt.isEmpty()){
            throw new IllegalArgumentException("Credit Account Number does not exist!");
        }
        Account creditAccount=creditAccountOpt.get();
        Account debitAccount=account.get();

        if (debitAccount.getAccountNumber().equals(creditAccount.getAccountNumber())){
            throw new IllegalArgumentException("You can not do transactions at the same Debit and Credit Account!");
        }

        if (!debitAccount.getCurrency().equals(creditAccount.getCurrency())){
            throw new IllegalArgumentException("Debit and Credit Account must have the same currency!");
        }

        if (debitAccount.getAccount_type().equals(AccountType.Loan)) {
            throw new IllegalArgumentException("You cannot debit a loan account!");
        }
        double amount=transaction.getAmount();
        if (debitAccount.getBalance()<amount){
            throw new IllegalArgumentException("This account does not have sufficient balance!");
        }

        debitAccount.setBalance(debitAccount.getBalance()-amount);
        creditAccount.setBalance(creditAccount.getBalance()+amount);

        accountRepository.save(debitAccount);
        accountRepository.save(creditAccount);

        transaction.setDebitAccount(debitAccount);
        transaction.setCreditAccount(creditAccount.getAccountNumber());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactionsByDebitAccountId(Long id){
        return transactionRepository.findAllByDebitAccountId(id);
    }

    public List<Transaction> getAllTransactionsByCreditAccountNumber(String creditAccountNumber){
        return transactionRepository.findAllByCreditAccountContains(creditAccountNumber);
    }


    public List<Transaction> getAllTransactionsByDebitIdBetweenDate(Long id,Date startDate, Date endDate){
        return transactionRepository.findTransactionsByDebitAccountIdAndTransactionDateBetween(id, startDate,endDate);
    }

    public List<Transaction> getAllTransactionsByCreditIdBetweenDate(String accountNumber,Date startDate, Date endDate){
        return transactionRepository.findTransactionsByCreditAccountAndTransactionDateBetween(accountNumber, startDate,endDate);
    }
}