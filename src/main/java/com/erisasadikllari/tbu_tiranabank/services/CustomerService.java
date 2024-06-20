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
        //Personal number taken
        if (potentionalUser.isPresent()){
            result.rejectValue("email", "PersonalNumberRegistered", "Personal number is already in use!");
        }
        //Password doesn't match confirmation
        if (!newCustomer.getPassword().equals(newCustomer.getConfirm())){
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        //Return null if result has errors
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

        //User doesn't exist
        if (!potentionalUser.isPresent()){
            result.rejectValue("email", "EmailNotFound", "No user found with that email address");
        }
        else {
            if (!BCrypt.checkpw(newLogin.getPassword(), potentionalUser.get().getPassword())){
                //BCrypt password match fails
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
