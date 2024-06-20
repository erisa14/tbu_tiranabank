package com.erisasadikllari.tbu_tiranabank.controllers;

import com.erisasadikllari.tbu_tiranabank.models.Account;
import com.erisasadikllari.tbu_tiranabank.models.AccountType;
import com.erisasadikllari.tbu_tiranabank.models.Currency;
import com.erisasadikllari.tbu_tiranabank.services.AccountService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/createAccountView")
    public String createAccountView(@ModelAttribute("newAccount") Account newAccount, HttpSession session, Model model){
        if(session.getAttribute("loggedInCustomerId") == null) {
            return "redirect:/logout";
        }
        model.addAttribute("accountTypes", AccountType.values());
        model.addAttribute("currencies", Currency.values());
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@Valid @ModelAttribute("newAccount")Account newAccount,Model model, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            model.addAttribute("accountTypes", AccountType.values());
            model.addAttribute("currencies", Currency.values());
            return "createAccount";
        }

        Long customerId=(Long) session.getAttribute("loggedInCustomerId");
        accountService.createAccount(newAccount,customerId);
        return "redirect:/dashboard";
    }
}
