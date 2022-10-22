package com.unq.desa.criptoP2P.model.quotation;

import com.unq.desa.criptoP2P.model.enums.operation.Operation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String activeCripto;

    private LocalDateTime dayAndTime;

    private int QuotationOfActiveCripto;

    private Operation operation;

}
