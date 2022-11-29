package com.unq.desa.criptoP2P.model.dto;


import com.unq.desa.criptoP2P.model.enums.operation.Operation;
import lombok.Data;

@Data
public class IntentionDto {

    private CryptoOcurrencyDto activeCripto;

    private QuotationDto quotation;

    private Integer amountOfOperationInPesos;

    private UserDetailDto userCripto;

    private Boolean isActive;

    private Operation operacion;
}
