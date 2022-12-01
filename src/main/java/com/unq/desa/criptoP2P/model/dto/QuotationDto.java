package com.unq.desa.criptoP2P.model.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@Hidden
public class QuotationDto implements Serializable {
    private String symbolCryptocurrency;

    private LocalDateTime dayAndTime;
}
