package com.unq.desa.criptoP2P.model.transaction;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.quotation.Quotation;
import com.unq.desa.criptoP2P.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime dayAndTimeOfIntention;

    @ManyToOne
    private Cryptocurrency activeCripto;

    private int amountOfActiveCripto;

    @ManyToOne
    private Quotation quotation;

    private Double amountOfOperationInPesos;
    @ManyToOne()
    private User user;

    private int reputationOfUser;

    private StateTransaction transaction;

}
