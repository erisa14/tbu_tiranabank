package com.erisasadikllari.tbu_tiranabank.controllers;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import com.erisasadikllari.tbu_tiranabank.services.AccountService;
import com.erisasadikllari.tbu_tiranabank.services.TransactionService;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/createTransactionView")
    public String createTransactionView(@ModelAttribute("newTransaction")Transaction newTransaction, HttpSession session, Model model){
        if(session.getAttribute("loggedInCustomerId") == null) {
            return "redirect:/logout";
        }

        Long customerId=(Long) session.getAttribute("loggedInCustomerId");
        List<Account> accounts=accountService.getAllAccountsByCustomerId(customerId);
        model.addAttribute("accounts", accounts);
        return "createTransaction";
    }

    @PostMapping("/createTransaction")
    public String createTransaction(@Valid@ModelAttribute("newTransaction")Transaction newTransaction,
                                    BindingResult result,
                                    Model model){
        if(result.hasErrors()){
            return "createTransaction";
        }

        try {
            transactionService.createTransaction(newTransaction);
            return "redirect:/dashboard";
        } catch (IllegalArgumentException e) {
            model.addAttribute("transactionError", e.getMessage());
            return "createTransaction";
        }
    }

    @GetMapping("/accountTransactions/{id}/download/pdf")
    public void downloadTransactionsPDF(@PathVariable("id") Long id,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                        HttpServletResponse response) throws IOException {
        String accountNumber=accountService.getById(id).getAccountNumber();
        List<Transaction> transactionDebit=transactionService.getAllTransactionsByDebitIdBetweenDate(id,startDate,endDate);
        List<Transaction> transactionCredit=transactionService.getAllTransactionsByCreditIdBetweenDate(accountNumber,startDate,endDate);
        byte[] pdfBytes = transactionService.generatePDF(transactionDebit, transactionCredit);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");
        response.getOutputStream().write(pdfBytes);
    }

    @GetMapping("/accountTransactions/{id}/download/csv")
    public void downloadTransactionsCSV(@PathVariable("id") Long id,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                        HttpServletResponse response) throws IOException {
        String accountNumber=accountService.getById(id).getAccountNumber();
        List<Transaction> transactionDebit=transactionService.getAllTransactionsByDebitIdBetweenDate(id,startDate,endDate);
        List<Transaction> transactionCredit=transactionService.getAllTransactionsByCreditIdBetweenDate(accountNumber,startDate,endDate);
        byte[] csvBytes = transactionService.generateCSV(transactionDebit, transactionCredit);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.csv");
        response.getOutputStream().write(csvBytes);
    }



}