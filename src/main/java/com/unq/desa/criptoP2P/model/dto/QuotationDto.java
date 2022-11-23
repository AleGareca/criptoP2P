package com.unq.desa.criptoP2P.model.dto;

import lombok.Data;


import java.time.LocalDateTime;
@Data
public class QuotationDto {
    private CryptoOcurrencyDto cryptocurrency;

    private LocalDateTime dayAndTime;
}
