package com.unq.desa.criptoP2P.model.transaction;

import com.unq.desa.criptoP2P.model.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime dayAndTimeOfIntention;

    private String activeCripto;

    private int amountOfActiveCripto;

    private int quotation;

    private Double amountOfOperationInPesos;

    //private User user;

    private int amountOfOperation;

    private int reputationOfUser;

}
