package com.erisasadikllari.tbu_tiranabank.controllers;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.Transaction;
import com.erisasadikllari.tbu_tiranabank.services.AccountService;
import com.erisasadikllari.tbu_tiranabank.services.TransactionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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


}
