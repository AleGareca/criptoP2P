package com.unq.desa.criptoP2P.model.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Data;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@Hidden
public class QuotationDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String symbolCryptocurrency;

    private LocalDateTime dayAndTime;
}
