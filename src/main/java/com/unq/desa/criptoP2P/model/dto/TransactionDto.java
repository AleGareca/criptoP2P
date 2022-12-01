package com.unq.desa.criptoP2P.model.dto;


import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class TransactionDto {
    private int id;
    private StateTransaction stateTransaction;
    private IntentionDto intentionDto;
    private LocalDateTime dayAndTimeOfOperation;
    private String symbol;



}
