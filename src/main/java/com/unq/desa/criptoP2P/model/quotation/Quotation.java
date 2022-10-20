package com.unq.desa.criptoP2P.model.quotation;

import com.unq.desa.criptoP2P.model.enums.operation.Operation;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Quotation {

    private String activeCripto;

    private LocalDateTime dayAndTime;

    private int QuotationOfActiveCripto;

    private Operation operation;

}
