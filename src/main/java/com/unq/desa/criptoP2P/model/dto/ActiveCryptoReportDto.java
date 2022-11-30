package com.unq.desa.criptoP2P.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ActiveCryptoReportDto {
    private LocalDateTime date;
    private Double dolarValue;
    private Double argValue;
    private List<ActivosDto> activosDto;
}
