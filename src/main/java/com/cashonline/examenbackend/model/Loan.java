package com.cashonline.examenbackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    private Double total;

    private Long userId;

}
