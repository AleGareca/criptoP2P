package com.unq.desa.criptoP2P.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRegisterIntetionDto {
    private String symbol;

    @JsonProperty("value_ars")
    private Double price;

    @JsonProperty("state_operation")
    private String state;

    private Boolean isActive;
}
