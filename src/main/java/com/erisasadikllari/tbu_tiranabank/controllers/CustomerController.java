package com.erisasadikllari.tbu_tiranabank.controllers;

import com.erisasadikllari.tbu_tiranabank.models.Customer;
import com.erisasadikllari.tbu_tiranabank.models.LoginCustomer;
import com.erisasadikllari.tbu_tiranabank.services.CustomerService;
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
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newCustomer")Customer newCustomer,
                        @ModelAttribute("newLogin")Customer newLogin, HttpSession session){
        Long loggedInCustomerId=(Long) session.getAttribute("loggedInCustomerId");

        if (loggedInCustomerId!=null){
            return "redirect: /dashboard";
        }
        model.addAttribute("newCustomer", new Customer());
        model.addAttribute("newLogin", new LoginCustomer());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newCustomer")Customer newCustomer, BindingResult result,
                           Model model, HttpSession session){
        customerService.register(newCustomer, result);
        if (result.hasErrors()){
            model.addAttribute("newLogin", new LoginCustomer());
            return "index";
        }
        session.setAttribute("loggedInCustomerId", newCustomer.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin")LoginCustomer newLogin, BindingResult result,
                        Model model, HttpSession session){
        Customer customer=customerService.login(newLogin, result);

        if (result.hasErrors()){
            model.addAttribute("newCustomer", new Customer());
            return "index";
        }
        session.setAttribute("loggedInCustomerId", customer.getId());
        return "redirect:/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        Long loggedInCustomerId=(Long) session.getAttribute("loggedInCustomerId");
        if (loggedInCustomerId==null){
            return "redirect:/";
        }
        Customer loggedInUser=customerService.findCustomerById(loggedInCustomerId);
        model.addAttribute("user",loggedInUser);
        return "dashboard";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
