package com.unq.desa.criptoP2P.model.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Data
@Hidden
public class CryptoOcurrencyDto {
    private String symbol;
    private Double price;
}
