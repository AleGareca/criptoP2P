package com.unq.desa.criptoP2P.model.dto;

import com.unq.desa.criptoP2P.model.Intencion.Intention;
import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import com.unq.desa.criptoP2P.model.user.User;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransactionDto {
    private LocalDateTime dayAndTimeOfOperation;

    private Cryptocurrency cripto;

    private Integer amountOfOperation;

    private Double amountOfOperationInPesos;
    private User user;

    private Integer numberOfOperations;

    private Integer reputationOfUser;

    private StateTransaction stateTransaction;

    private Intention intention;

    private String shippingAddress;
}
