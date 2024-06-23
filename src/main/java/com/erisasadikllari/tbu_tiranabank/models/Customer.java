package com.erisasadikllari.tbu_tiranabank.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "Firstname is required!")
    @Size(min = 3, max = 30, message = "Firstname must be between 3 and 30 characters")
    private String firstname;

    @NotEmpty(message = "Lastname is required!")
    @Size(min = 3, max = 30, message = "Lastname must be between 3 and 30 characters")
    private String lastname;

    @NotEmpty(message = "Personal number is required!")
    @Pattern(regexp = "^[a-zA-Z]\\d{8}[a-zA-Z]$", message = "Personal Number-Id Invalid format Expected pattern: Letter-Numbers-Letter")
    private  String personalNr;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    private String email;


    @NotEmpty(message = "Password is required!")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    private String password;

    @Transient
    @NotEmpty(message = "Confirm Password is required!")
    @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
    private String confirm;

    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    @Column(updatable=false)
    @OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Account> customer_accounts;

//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id=" + id +
//                ", firstname='" + firstname + '\'' +
//                ",lastname"+lastname+'\''+
//                ", email='" + email + '\'' +
//                ", personalNr='" + personalNr + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

}
