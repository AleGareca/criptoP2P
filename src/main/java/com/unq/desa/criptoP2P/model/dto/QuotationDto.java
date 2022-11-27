package com.unq.desa.criptoP2P.model.dto;

import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class QuotationDto implements Serializable {
    private String symbolCryptocurrency;

    private LocalDateTime dayAndTime;
}
