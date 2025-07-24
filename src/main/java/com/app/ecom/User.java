package com.app.ecom;

import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;


}
