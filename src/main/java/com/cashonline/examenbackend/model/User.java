package com.cashonline.examenbackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String firstName;

    private String lastName;


    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<Loan> loans;
}
