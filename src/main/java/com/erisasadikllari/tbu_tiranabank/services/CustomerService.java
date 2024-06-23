package com.erisasadikllari.tbu_tiranabank.services;

import com.erisasadikllari.tbu_tiranabank.models.Customer;
import com.erisasadikllari.tbu_tiranabank.models.LoginCustomer;
import com.erisasadikllari.tbu_tiranabank.repositories.CustomerRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer register(Customer newCustomer, BindingResult result){
        Optional<Customer> potentionalUser=this.customerRepository.findByPersonalNr(newCustomer.getPersonalNr());
        if (potentionalUser.isPresent()){
            result.rejectValue("email", "PersonalNumberRegistered", "Personal number is already in use!");
        }
        if (!newCustomer.getPassword().equals(newCustomer.getConfirm())){
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        if (result.hasErrors()){
            return null;
        }
        //Save customer
        else{
            String hashed= BCrypt.hashpw(newCustomer.getPassword(), BCrypt.gensalt());
            newCustomer.setPassword(hashed);
            return customerRepository.save(newCustomer);
        }
    }

    public Customer login(LoginCustomer newLogin, BindingResult result){
        Optional<Customer> potentionalUser=this.customerRepository.findByPersonalNr(newLogin.getPersonalNr());

        if (!potentionalUser.isPresent()){
            result.rejectValue("personalNr", "PersonalNumberNotFound", "No customer found with that personal number!");
        }
        else {
            if (!BCrypt.checkpw(newLogin.getPassword(), potentionalUser.get().getPassword())){
                result.rejectValue("password", "Matches", "Invalid password");
            }
        }
        //Return null if result has errors
        if (result.hasErrors()){
            return null;
        }
        else {
            return potentionalUser.get();
        }
    }


    public Customer findCustomerById(Long id){
        return this.customerRepository.findById(id).orElse(null);
    }
}
