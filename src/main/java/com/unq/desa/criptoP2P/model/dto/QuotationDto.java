package com.unq.desa.criptoP2P.model.dto;

import com.unq.desa.criptoP2P.model.cryptoCurrency.Cryptocurrency;
import lombok.Data;


import java.time.LocalDateTime;
@Data
public class QuotationDto {
    private CryptocurrencyDto cryptocurrency;

    private LocalDateTime dayAndTime;
}
