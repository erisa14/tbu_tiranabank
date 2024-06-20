package com.erisasadikllari.tbu_tiranabank.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginCustomer {

    @NotEmpty(message = "Personal number is required!")
    @Pattern(regexp = "^[a-zA-Z]\\d{8}[a-zA-Z]$", message = "Personal Number-Id Invalid format Expected pattern: Letter-Numbers-Letter")
    private String personalNr;

    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;



}
