package com.erisasadikllari.tbu_tiranabank.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 12, max = 12, message = "Account must contain 12 characters!")
    private String creditAccount;

    @NotBlank
    @Size(min = 10, max = 200, message = "Description must contain at least 10 characters!")
    private String description;

    @NotNull
    private double amount;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;

    @PrePersist
    protected void onCreate() {
        this.transactionDate = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account debitAccount;

}
