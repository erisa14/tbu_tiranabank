package com.erisasadikllari.tbu_tiranabank.controllers;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.AccountType;
import com.erisasadikllari.tbu_tiranabank.models.Currency;
import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import com.erisasadikllari.tbu_tiranabank.services.AccountService;
import com.erisasadikllari.tbu_tiranabank.services.TransactionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/createAccountView")
    public String createAccountView(@ModelAttribute("newAccount") Account newAccount, HttpSession session, Model model) {
        if (session.getAttribute("loggedInCustomerId") == null) {
            return "redirect:/logout";
        }
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("currencies", Currency.values());
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@Valid @ModelAttribute("newAccount") Account newAccount, Model model, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("accountTypes", AccountType.values());
            model.addAttribute("currencies", Currency.values());
            return "createAccount";
        }

        Long customerId = (Long) session.getAttribute("loggedInCustomerId");
        accountService.createAccount(newAccount, customerId);
        return "redirect:/dashboard";
    }

    @GetMapping("/userAccounts")
    public String getAllUserAccounts(HttpSession session, Model model) {
        if (session.getAttribute("loggedInCustomerId") == null) {
            return "redirect:/logout";
        }
        Long customerId = (Long) session.getAttribute("loggedInCustomerId");
        List<Account> accounts = accountService.getAllAccountsByCustomerId(customerId);
        model.addAttribute("accounts", accounts);
        return "userAccounts";
    }

    @GetMapping("/account/{id}/details")
    public String getAccountDetails(@PathVariable("id") Long id, HttpSession session, Model model) {
        if (session.getAttribute("loggedInCustomerId") == null) {
            return "redirect:/logout";
        }

        Account account = accountService.getById(id);
        List<Transaction> transactionsDebit = transactionService.getAllTransactionsByDebitAccountId(id);
        List<Transaction> transactionsCredit = transactionService.getAllTransactionsByCreditAccountNumber(account.getAccountNumber());

                model.addAttribute("account", account);
                model.addAttribute("transactionsDebit", transactionsDebit);
                model.addAttribute("transactionsCredit", transactionsCredit);
                return "accountDetails";
    }
}
