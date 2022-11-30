package com.unq.desa.criptoP2P.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unq.desa.criptoP2P.model.enums.stateTransaction.StateTransaction;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class TransactionDto {
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime dayAndTimeOfOperation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String symbolCripto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer amountOfOperation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double amountOfOperationInPesos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StateTransaction stateTransaction;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String shippingAddress;


}
