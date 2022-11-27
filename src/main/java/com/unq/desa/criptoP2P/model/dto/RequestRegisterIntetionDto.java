package com.unq.desa.criptoP2P.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestRegisterIntetionDto {
    private String symbol;

    @JsonProperty("value_ars")
    private Integer price;

    @JsonProperty("state_operation")
    private String state;

    private Boolean isActive;
}
