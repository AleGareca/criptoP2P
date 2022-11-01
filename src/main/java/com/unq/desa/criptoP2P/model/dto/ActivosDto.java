package com.unq.desa.criptoP2P.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ActivosDto {
    private String symbol;
    private Double price;
}
