package com.unq.desa.criptoP2P.model.dto;

import lombok.Data;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class QuotationDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String symbolCryptocurrency;

    private LocalDateTime dayAndTime;
}
