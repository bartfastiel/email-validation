package com.example.emailvalidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

record Contact(
        @Email
        String email) {
}

@SpringBootApplication
public class EmailValidationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmailValidationApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello World from EmailValidationApplication");
        //String email = "daniel.schwarz@neuefische.de";
        String email = "daniel.schwarz@";
        Contact daniel = new Contact(email);
        System.out.println(daniel);

        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Set<ConstraintViolation<Contact>> violations = validatorFactory.getValidator().validate(daniel);
            if (!violations.isEmpty()) {
                throw new IllegalArgumentException("Invalid email: " + email);
            }
        }
    }
}
